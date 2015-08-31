package com.techscl.lovechat;

import android.app.Application;
import android.content.Context;

import com.easemob.chat.EMChat;

/**
 * Created by dllo on 15/8/28.
 */
public class LoveChat extends Application {
    public static Context loveChatContext;

    public static Context getLoveChatContext() {
        return loveChatContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        EMChat.getInstance().setAutoLogin(true);// 自动登录

        EMChat.getInstance().init(this);// 初始化

        EMChat.getInstance().setDebugMode(true);// 代码混淆需要设置FALSE

        loveChatContext = getApplicationContext();

    }
}
