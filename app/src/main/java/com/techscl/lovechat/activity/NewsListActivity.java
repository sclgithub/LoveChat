package com.techscl.lovechat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.techscl.lovechat.R;
import com.techscl.lovechat.adapter.NewsListAdapter;
import com.techscl.lovechat.base.GestureActivity;
import com.techscl.lovechat.base.News;
import com.techscl.lovechat.base.NewsListSAX;
import com.techscl.lovechat.base.StreamRequest;
import com.techscl.lovechat.base.SwipeRefreshLoadingLayout;
import com.techscl.lovechat.utils.L;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by 宋春麟 on 15/9/1.
 */
public class NewsListActivity extends GestureActivity {
    private ListView news_list;
    private NewsListAdapter listAdapter;
    private Handler handler;
    private SwipeRefreshLoadingLayout srff;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        news_list = (ListView) findViewById(R.id.news_list);
        srff = (SwipeRefreshLoadingLayout) findViewById(R.id.srff_reconmmend);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.news_rss);

        toolbar.setNavigationIcon(R.mipmap.back);



        getList();

        refresh();

    }

    /**
     * 获取数据
     */
    private void getList() {
        StreamRequest request = new StreamRequest("http://api.ithome.com/xml/newslist/news.xml",
                new Response.Listener<InputStream>() {
                    @Override
                    public void onResponse(InputStream inputStream) {
                        L.i("news拉取成功" + inputStream);
                        handler = new Handler(new Handler.Callback() {
                            @Override
                            public boolean handleMessage(Message message) {
                                if (message.what == 10) {
                                    final List<News> list = (List<News>) message.obj;
                                    listAdapter = new NewsListAdapter(NewsListActivity.this, list);
                                    news_list.setAdapter(listAdapter);
                                    srff.setRefreshing(false);

                                    news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                            Intent newsDetail = new Intent(NewsListActivity.this, NewsDetailActivity.class);
                                            newsDetail.putExtra("newsId", list.get(i).getNewsid());
                                            newsDetail.putExtra("title", list.get(i).getTitle());
                                            newsDetail.putExtra("postdate", list.get(i).getPostdate());
                                            newsDetail.putExtra("hitCount", list.get(i).getHitcount());
                                            newsDetail.putExtra("image", list.get(i).getImage());
                                            startActivity(newsDetail);


                                        }
                                    });
                                }
                                return false;
                            }
                        });
                        Log.i("网络拉取成功", " recommend");
                        SAXParserFactory spx = SAXParserFactory.newInstance();
                        try {
                            SAXParser sp = spx.newSAXParser();
                            sp.parse(inputStream, new NewsListSAX(handler));

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

            }
        });
        MainActivity.requestQueue.add(request);
    }

    /**
     * 刷新
     */
    private void refresh() {
        srff.setOnRefreshListener(new SwipeRefreshLoadingLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getList();

            }
        });
        srff.setOnLoadListener(new SwipeRefreshLoadingLayout.OnLoadListener() {
            @Override
            public void onLoad() {

                srff.setLoading(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }
}
