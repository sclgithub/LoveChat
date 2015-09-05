package com.techscl.lovechat;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;
import com.techscl.lovechat.receiver.NewMessageBroadcastReceiver;

/**
 * Created by dllo on 15/8/28.
 */
public class LoveChat extends Application {
    public static Context loveChatContext;
    private static LoveChat instance;
    public static LoveChatHXSDKHelper hxSDKHelper=new LoveChatHXSDKHelper();
    private BroadcastReceiver ackMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            abortBroadcast();
            String msgid = intent.getStringExtra("msgid");
            String from = intent.getStringExtra("from");
            EMConversation conversation = EMChatManager.getInstance().getConversation(from);
            if (conversation != null) {
                // 把message设为已读
                EMMessage msg = conversation.getMessage(msgid);
                if (msg != null) {
                    msg.isAcked = true;
                }
            }

        }
    };

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

        EMChatManager.getInstance().getChatOptions().setUseRoster(true);

        NewMessageBroadcastReceiver msgReceiver = new NewMessageBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(EMChatManager.getInstance().getNewMessageBroadcastAction());
        intentFilter.setPriority(3);
        registerReceiver(msgReceiver, intentFilter);

        EMChatManager.getInstance().getChatOptions().setRequireAck(false);
        //如果用到已读的回执需要把这个flag设置成true

        IntentFilter ackMessageIntentFilter = new IntentFilter(EMChatManager.getInstance().getAckMessageBroadcastAction());
        ackMessageIntentFilter.setPriority(3);
        registerReceiver(ackMessageReceiver, ackMessageIntentFilter);
    }

    public static LoveChat getInstance(){
        return instance;
    }

    /**
     * 获取当前登陆用户名
     *
     * @return
     */
    public String getUserName() {
        return hxSDKHelper.getHXId();
    }

    /**
     * 获取密码
     *
     * @return
     */
    public String getPassword() {
        return hxSDKHelper.getPassword();
    }

    /**
     * 设置用户名
     *
     */
    public void setUserName(String username) {
        hxSDKHelper.setHXId(username);
    }

    /**
     * 设置密码 下面的实例代码 只是demo，实际的应用中需要加password 加密后存入 preference 环信sdk
     * 内部的自动登录需要的密码，已经加密存储了
     *
     * @param pwd
     */
    public void setPassword(String pwd) {
        hxSDKHelper.setPassword(pwd);
    }

    /**
     * 退出登录,清空数据
     */
    public void logout(final boolean isGCM,final EMCallBack emCallBack) {
        // 先调用sdk logout，在清理app中自己的数据
        hxSDKHelper.logout(isGCM,emCallBack);
    }

}
