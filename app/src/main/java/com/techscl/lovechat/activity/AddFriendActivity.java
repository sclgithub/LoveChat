package com.techscl.lovechat.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.techscl.lovechat.R;
import com.techscl.lovechat.base.GestureActivity;

/**
 * Created by 宋春麟 on 15/8/31.
 */
public class AddFriendActivity extends GestureActivity {
    private LinearLayout add_friend_layout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 设置透明状态栏

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
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
