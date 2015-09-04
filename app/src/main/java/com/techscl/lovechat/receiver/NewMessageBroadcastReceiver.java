package com.techscl.lovechat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMMessage;

/**
 * Created by songchunlin on 15/9/3.
 */
public class NewMessageBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        abortBroadcast();

        // 消息id（每条消息都会生成唯一的一个id，目前是SDK生成）
        String msgId = intent.getStringExtra("msgid");
        //发送方
        String username = intent.getStringExtra("from");
        // 收到这个广播的时候，message已经在db和内存里了，可以通过id获取mesage对象
        EMMessage message = EMChatManager.getInstance().getMessage(msgId);
        EMConversation conversation = EMChatManager.getInstance().getConversation(username);
        // 如果是群聊消息，获取到group id
        if (message.getChatType() == EMMessage.ChatType.GroupChat) {
            username = message.getTo();
        }
        if (!username.equals(username)) {
            // 消息不是发给当前会话，return
            return;
        }
    }
}
