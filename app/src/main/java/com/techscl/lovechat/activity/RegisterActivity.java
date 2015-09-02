package com.techscl.lovechat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.exceptions.EaseMobException;
import com.techscl.lovechat.LoveChat;
import com.techscl.lovechat.R;
import com.techscl.lovechat.base.GestureActivity;
import com.techscl.lovechat.utils.To;

/**
 * Created by 宋春麟 on 15/8/31.
 */
public class RegisterActivity extends GestureActivity implements View.OnClickListener {
    private EditText username, password, affirm_password;
    private Button register;
    private LinearLayout register_layout;
    private Context context;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 设置透明状态栏
        initView();

    }

    /**
     * 初始化
     */
    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        affirm_password = (EditText) findViewById(R.id.affirm_password);
        register = (Button) findViewById(R.id.register);
        register_layout = (LinearLayout) findViewById(R.id.register_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.register);
        register.setOnClickListener(this);
        register_layout.setOnTouchListener(this);
    }

    /**
     * 点击事件监听
     *
     * @param view 组件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register:
                if (affirm_password.getText().toString().equals(password.getText().toString())) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            startRegister();
                        }
                    }).start();

                } else {
                    To.showShort(RegisterActivity.this, "两次输入不一致");
                }
                break;
            default:
                break;
        }
    }

    /**
     * 注册方法
     */
    private void startRegister() {
        try {
            EMChatManager.getInstance().createAccountOnServer(username.getText().toString(), affirm_password.getText().toString());
            Intent result = new Intent(RegisterActivity.this, LoginActivity.class);
            result.putExtra("username", username.getText().toString());
            result.putExtra("password", password.getText().toString());
            setResult(10, result);
            finish();
        } catch (final EaseMobException e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int errorCode = e.getErrorCode();
                    if (errorCode == EMError.NONETWORK_ERROR) {
                        To.showShort(LoveChat.getLoveChatContext(), "网络异常");
                    } else if (errorCode == EMError.USER_ALREADY_EXISTS) {
                        To.showShort(LoveChat.getLoveChatContext(), "用户名已被注册");
                    } else if (errorCode == EMError.UNAUTHORIZED) {
                        To.showShort(LoveChat.getLoveChatContext(), "权限不足");
                    } else {
                        To.showShort(LoveChat.getLoveChatContext(), e.getMessage());
                    }
                }
            });
        }
    }

}
