package com.techscl.lovechat;

import android.app.Application;
import android.content.Context;

import com.easemob.chat.EMChat;

/**
 * Created by dllo on 15/8/28.
 */
public class LoveChat extends Application {
    private Context loveChatContext;

    @Override
    public void onCreate() {
        super.onCreate();
        EMChat.getInstance().init(loveChatContext);// 初始化

        EMChat.getInstance().setDebugMode(true);// 代码混淆需要设置FALSE
    }
}
