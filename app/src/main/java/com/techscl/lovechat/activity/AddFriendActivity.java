package com.techscl.lovechat.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;

import com.techscl.lovechat.R;
import com.techscl.lovechat.base.GestureActivity;

/**
 * Created by dllo on 15/8/31.
 */
public class AddFriendActivity extends GestureActivity {
    private LinearLayout add_friend_layout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        initView();

    }

    /**
     * 初始化
     */
    private void initView() {
        add_friend_layout = (LinearLayout) findViewById(R.id.add_friend_layout);
        add_friend_layout.setOnTouchListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.add_friend);
    }
}
