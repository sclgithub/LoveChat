package com.techscl.lovechat.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactManager;
import com.easemob.exceptions.EaseMobException;
import com.techscl.lovechat.R;
import com.techscl.lovechat.base.GestureActivity;
import com.techscl.lovechat.utils.L;

/**
 * Created by 宋春麟 on 15/8/31.
 */
public class AddFriendActivity extends GestureActivity implements View.OnClickListener {
    private LinearLayout add_friend_layout;
    private Toolbar toolbar;
    private EditText input_username;
    private Button add;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 设置透明状态栏
        EMChatManager.getInstance().getChatOptions().setUseRoster(true);
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
        input_username = (EditText) findViewById(R.id.input_username);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMContactManager.getInstance().addContact(input_username.getText().toString(), ",,,,");
                            L.i("添加" + input_username.getText());
                        } catch (EaseMobException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }
}
