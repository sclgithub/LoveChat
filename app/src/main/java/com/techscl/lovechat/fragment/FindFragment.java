package com.techscl.lovechat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.techscl.lovechat.R;
import com.techscl.lovechat.activity.MainActivity;
import com.techscl.lovechat.activity.NewsListActivity;
import com.techscl.lovechat.base.Weather;
import com.techscl.lovechat.utils.FormatCodeUtil;
import com.techscl.lovechat.utils.L;

/**
 * Created by 宋春麟 on 15/8/27.
 */
public class FindFragment extends Fragment implements View.OnClickListener {
    private TableRow news_rss;
    private TextView weather, max_temp, min_temp, wind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find, null);

        initView(view);

        getWeather(FormatCodeUtil.codingFormat("大连"));

        return view;

    }

    private void getWeather(String area) {
        StringRequest stringRequest = new StringRequest("http://api.lib360.net/open/weather.json?city=" + area, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();// 实例化gson对象
                Weather weathers = gson.fromJson(s, Weather.class);
                L.i("天气:" + s);
                weather.setText(weathers.getDatanow().getWeather()+"");
                max_temp.setText(weathers.getDatanow().getTempMax() + "");
                min_temp.setText(weathers.getDatanow().getTempMin() + "");
                wind.setText(weathers.getDatanow().getWind().toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MainActivity.requestQueue.add(stringRequest);
    }

    /**
     * 初始化
     *
     * @param view
     */
    private void initView(View view) {
        news_rss = (TableRow) view.findViewById(R.id.news_rss);
        weather = (TextView) view.findViewById(R.id.weather);
        max_temp = (TextView) view.findViewById(R.id.max_temp);
        min_temp = (TextView) view.findViewById(R.id.min_temp);
        wind = (TextView) view.findViewById(R.id.wind);
        news_rss.setOnClickListener(this);
    }

    /**
     * 点击监听
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.news_rss:
                Intent news_rss = new Intent(getActivity(), NewsListActivity.class);
                startActivity(news_rss);
                break;
        }
    }

}
