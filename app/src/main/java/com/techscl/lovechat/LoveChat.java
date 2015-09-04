package com.techscl.lovechat;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

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

}
