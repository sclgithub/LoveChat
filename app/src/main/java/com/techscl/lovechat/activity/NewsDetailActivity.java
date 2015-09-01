package com.techscl.lovechat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.techscl.lovechat.R;
import com.techscl.lovechat.adapter.RelatedListAdapter;
import com.techscl.lovechat.base.GestureActivity;
import com.techscl.lovechat.base.MyStringRequest;
import com.techscl.lovechat.base.News;
import com.techscl.lovechat.base.NewsDetialSAX;
import com.techscl.lovechat.base.StreamRequest;
import com.techscl.lovechat.utils.FormatCodeUtil;
import com.techscl.lovechat.utils.L;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by 宋春麟 on 15/8/5.
 */
public class NewsDetailActivity extends GestureActivity implements View.OnTouchListener, GestureDetector.OnGestureListener {
    List<News> list;
    int totalHeight = 0;
    private String url = null;
    private String newsId;
    private Handler handler;
    private RelativeLayout composerButtonsWrapper;
    private TextView title, postDate, newsSource, newsAuthor, hitCount;
    private WebView newsDetail;
    private ListView correlation;
    private TextToSpeech speech;
    private FrameLayout frameLayout;
    private RequestQueue requestQueue;
    private Intent intent;
    private RelatedListAdapter relatedListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_detial);// 加载布局

        initView();// 初始化

        frameLayout.setOnTouchListener(this);// 为整个页面设置触摸监听

        intent = getIntent();// 定义一个intent接收对象,获取id等数据

        newsId = intent.getStringExtra("newsId");// 得到id
        title.setText(intent.getStringExtra("title"));
        getDetail(newsId, intent);// 调用网络请求


    }

    /**
     * 初始化
     */
    private void initView() {
        correlation = (ListView) findViewById(R.id.correlation);//实例化组件
        requestQueue = Volley.newRequestQueue(this);
        composerButtonsWrapper = (RelativeLayout) findViewById(R.id.composer_buttons_wrapper);

        frameLayout = (FrameLayout) findViewById(R.id.total);
        title = (TextView) findViewById(R.id.title);

        postDate = (TextView) findViewById(R.id.postDate);
        newsSource = (TextView) findViewById(R.id.newsSource);
        newsAuthor = (TextView) findViewById(R.id.newsAuthor);
        hitCount = (TextView) findViewById(R.id.hitCount);
        newsDetail = (WebView) findViewById(R.id.newsDetail);
    }

    /**
     * 网络拉取
     *
     * @param newsId 新闻id
     * @param intent intent对象
     */
    private void getDetail(String newsId, final Intent intent) {
        String id1 = newsId.substring(0, 3);// 分割id,用于拼接到地址中
        String id2 = newsId.substring(3, 6);
        L.v("传入的新闻ID", id1 + "/" + id2);
        StreamRequest request = new StreamRequest("http://api.ithome.com/xml/newscontent/" + id1 + "/" + id2 + ".xml",
                new Response.Listener<InputStream>() {
                    /**
                     * 请求成功,显示到WebView
                     * @param inputStream
                     */
                    @Override
                    public void onResponse(InputStream inputStream) {
                        /*接收解析完成的数据并显示到WebView*/
                        handler = new Handler(new Handler.Callback() {
                            @Override
                            public boolean handleMessage(Message message) {
                                if (message.what == 100) {
                                    list = (List<News>) message.obj;
                                    for (News news : list) {

                                        title.setText(intent.getStringExtra("title"));
                                        postDate.setText(intent.getStringExtra("postdate"));
                                        newsSource.setText(news.getNewssource());
                                        newsAuthor.setText(news.getNewsauthor());
                                        hitCount.setText(intent.getStringExtra("hitCount"));
                                        Document doc_Dis = Jsoup.parse(news.getDetail());
                                        Elements ele_Img = doc_Dis.getElementsByTag("img");
                                        if (ele_Img.size() != 0) {
                                            for (org.jsoup.nodes.Element e_Img : ele_Img) {
                                                e_Img.attr("style", "width:100%");
                                            }
                                        }
                                        final String newHtmlContent = doc_Dis.toString();

                                        newsDetail.loadDataWithBaseURL("about:blank", newHtmlContent, "text/html", "utf-8", null);// 显示到WebView
                                        WebSettings settings = newsDetail.getSettings();
                                        settings.setJavaScriptEnabled(true);
                                        settings.setJavaScriptCanOpenWindowsAutomatically(true);

                                        newsDetail.setOnTouchListener(new View.OnTouchListener() {
                                            @Override
                                            public boolean onTouch(View view, MotionEvent motionEvent) {

                                                return false;
                                            }
                                        });

                                        String newTag = FormatCodeUtil.codingFormat(news.getTags());//新闻关键字进行转码,拼接到相关新闻的地址中

                                        /*请求相关新闻列表的request*/
                                        MyStringRequest stringRequest = new MyStringRequest("http://www.ithome.com/json/tags/" + newTag + ".json",
                                                new Response.Listener<String>() {
                                                    @Override
                                                    public void onResponse(String s) {

                                                        if (s != null && s.length() > 2) {
                                                            String[] temp = s.split("=");//对拉取到的json数据进行规范操作,便于解析
                                                            Gson gson = new Gson();// 实例化gson对象
                                                            Type type = new TypeToken<ArrayList<News>>() {
                                                            }.getType();
                                                            final List<News> vartag_jsonp = gson.fromJson(temp[1], type);// 利用gson解析JSON数组

                                                            relatedListAdapter = new RelatedListAdapter(NewsDetailActivity.this, vartag_jsonp);// 实例化适配器

                                                            correlation.setAdapter(relatedListAdapter);//设置到适配器

                                                            for (int i = 0, len = relatedListAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
                                                                View listItem = relatedListAdapter.getView(i, null, correlation);
                                                                listItem.measure(0, 0); // 计算子项View 的宽高
                                                                totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
                                                            }
                                                            ViewGroup.LayoutParams params = correlation.getLayoutParams();
                                                            params.height = totalHeight + (correlation.getDividerHeight() * (relatedListAdapter.getCount()));
                                                            correlation.setLayoutParams(params);

                                                            correlation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                                /*监听相关新闻列表*/
                                                                @Override
                                                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                                    Intent newsDetail = new Intent(NewsDetailActivity.this, NewsDetailActivity.class);// 新建intent对象,跳转本身
                                                                    newsDetail.putExtra("newsId", vartag_jsonp.get(i).getNewsid());//向目标传递数据
                                                                    newsDetail.putExtra("title", vartag_jsonp.get(i).getNewstitle());
                                                                    newsDetail.putExtra("postdate", vartag_jsonp.get(i).getPostdate());
                                                                    startActivity(newsDetail);// 开始跳转
                                                                }
                                                            });
                                                        }
                                                    }
                                                }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError volleyError) {

                                            }
                                        });
                                        requestQueue.add(stringRequest);
                                    }

                                }
                                return false;
                            }
                        });
                        /*将获取成功的数据调用xml解析方法进行解析*/
                        SAXParserFactory spx = SAXParserFactory.newInstance();
                        try {
                            SAXParser sp = spx.newSAXParser();
                            sp.parse(inputStream, new NewsDetialSAX(handler));

                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("网络拉取成功", volleyError.toString());
            }
        });
        requestQueue.add(request);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newsDetail.reload();// 销毁时重新加载
    }

}
