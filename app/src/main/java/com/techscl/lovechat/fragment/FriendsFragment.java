package com.techscl.lovechat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.techscl.lovechat.R;

/**
 * Created by 宋春麟 on 15/8/27.
 */
public class FriendsFragment extends Fragment {
    private ListView friends_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, null);

        initView(view);

        return view;

    }

    /**
     * 页面初始化
     *
     * @param view 当前页
     */
    private void initView(View view) {
        friends_list = (ListView) view.findViewById(R.id.friends_list);
    }
}
