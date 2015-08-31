package com.techscl.lovechat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMGroupManager;
import com.techscl.lovechat.R;
import com.techscl.lovechat.base.GestureActivity;
import com.techscl.lovechat.utils.L;
import com.techscl.lovechat.utils.To;

/**
 * Created by dllo on 15/8/28.
 */
public class LoginActivity extends GestureActivity implements View.OnClickListener {
    private TextInputLayout til_username, til_password;
    private EditText username, password;
    private Button register, login;
    private LinearLayout login_layout;
    private Toolbar toolbar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.login);
        login_layout = (LinearLayout) findViewById(R.id.login_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
        login_layout.setOnTouchListener(this);
        toolbar.setTitle(R.string.login);

    }

    /**
     * 点击事件监听
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:// 登录
                if (username.getText().toString().equals("")) {
                    To.showShort(this, "用户名不能为空");
                } else if (password.getText().toString().equals("")) {
                    To.showShort(this, "密码不能为空");
                } else {
                    startLogin();
                }
                break;
            case R.id.register:// 注册
                Intent register = new Intent(this, RegisterActivity.class);
                startActivityForResult(register, 101);

                break;
            default:
                L.i("没有这个事件");
                break;
        }
    }

    /**
     * 登录方法
     */
    private void startLogin() {
        EMChatManager.getInstance().login(username.getText().toString(), password.getText().toString(), new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        EMGroupManager.getInstance().loadAllGroups();
                        EMChatManager.getInstance().loadAllConversations();
                        To.showLong(LoginActivity.this, "登录成功");
                        /**
                         * 此处应从登录成功处写入
                         */
                        StartActivity.editor = StartActivity.preferences.edit();
                        StartActivity.editor.putBoolean("login", false);
                        StartActivity.editor.commit();
                        Intent login = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(login);
                        finish();
                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {
                L.i(status);
                To.showShort(context, status);
            }

            @Override
            public void onError(int code, String message) {
                L.i(message);
                To.showShort(context, message);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == 10) {
            username.setText(data.getStringExtra("username"));
            password.setText(data.getStringExtra("password"));
        }
    }
}
