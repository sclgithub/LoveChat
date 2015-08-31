package com.techscl.lovechat.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import com.techscl.lovechat.R;

/**
 * Created by dllo on 15/8/28.
 */
public class LoginActivity extends GestureActivity {
    private TextInputLayout til_username, til_password;
    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

    }


}
