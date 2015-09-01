package com.techscl.lovechat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.techscl.lovechat.R;
import com.techscl.lovechat.activity.NewsListActivity;

/**
 * Created by 宋春麟 on 15/8/27.
 */
public class FindFragment extends Fragment implements View.OnClickListener {
    private TableRow news_rss;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_find, null);

        initView(view);

        return view;

    }

    /**
     * 初始化
     *
     * @param view
     */
    private void initView(View view) {
        news_rss = (TableRow) view.findViewById(R.id.news_rss);
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
