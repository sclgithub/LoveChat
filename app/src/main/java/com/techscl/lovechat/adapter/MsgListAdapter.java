package com.techscl.lovechat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.easemob.chat.EMConversation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋春麟 on 15/8/28.
 */
public class MsgListAdapter extends ArrayAdapter<EMConversation> {
    private LayoutInflater inflater;
    private List<EMConversation> conversationList;
    private List<EMConversation> copyConversationList;
    private boolean notiyfyByFilter;

    public MsgListAdapter(Context context, int textViewResourceId, List<EMConversation> objects) {
        super(context, textViewResourceId, objects);
        this.conversationList = objects;
        copyConversationList = new ArrayList<EMConversation>();
        copyConversationList.addAll(objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return super.getView(position, convertView, parent);
    }
}
