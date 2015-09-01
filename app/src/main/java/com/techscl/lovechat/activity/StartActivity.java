package com.techscl.lovechat.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.techscl.lovechat.R;
import com.techscl.lovechat.utils.L;

/**
 * Created by dllo on 15/8/31.
 */
public class StartActivity extends Activity {

    public static SharedPreferences preferences;
    public static SharedPreferences.Editor editor;
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        /**
         * 读取登录状态,已经登录过进入MainActivity,否则进入LoginActivity
         */
        preferences = getSharedPreferences("auto_login", Context.MODE_PRIVATE);
        if (flag) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    if (preferences.getBoolean("login", true)) {
                        L.i("login");
                        Intent intent = new Intent(StartActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        L.i("main");
                        Intent intent = new Intent(StartActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }, 1111);
            flag = false;
        }
    }

}
