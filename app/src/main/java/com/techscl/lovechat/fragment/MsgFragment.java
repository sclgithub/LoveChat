package com.techscl.lovechat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.easemob.chat.EMChat;
import com.easemob.chat.EMContactListener;
import com.easemob.chat.EMContactManager;
import com.easemob.chat.EMConversation;
import com.techscl.lovechat.R;
import com.techscl.lovechat.adapter.MsgListAdapter;
import com.techscl.lovechat.customview.SlideCutListView;
import com.techscl.lovechat.utils.L;
import com.techscl.lovechat.utils.To;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 宋春麟 on 15/8/27.
 * hah
 */
public class MsgFragment extends Fragment {
    public LinearLayout msg_layout;
    private InputMethodManager inputMethodManager;
    private SlideCutListView msg_list;
    private MsgListAdapter msgListAdapter;
    private boolean hidden;
    private List<EMConversation> conversationList = new ArrayList<>();
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg, null);

        initView(view);

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        msg_layout = (LinearLayout) getView().findViewById(R.id.msg_layout);
        msg_list = (SlideCutListView) getView().findViewById(R.id.msg_list);
    }

    /**
     * 页面初始化
     *
     * @param view 当前页面
     */
    private void initView(View view) {
        msg_list = (SlideCutListView) view.findViewById(R.id.msg_list);
        EMContactManager.getInstance().setContactListener(new MyContactListener());
        EMChat.getInstance().setAppInited();
    }

    private class MyContactListener implements EMContactListener {

        @Override
        public void onContactAdded(List<String> usernameList) {
            // 保存增加的联系人

        }

        @Override
        public void onContactDeleted(final List<String> usernameList) {
            // 被删除

        }

        @Override
        public void onContactInvited(String username, String reason) {
            // 接到邀请的消息，如果不处理(同意或拒绝)，掉线后，服务器会自动再发过来，所以客户端不要重复提醒
            L.i(username + reason);
            To.showShort(username + reason + "");
        }

        @Override
        public void onContactAgreed(String username) {
            //同意好友请求
        }

        @Override
        public void onContactRefused(String username) {
            // 拒绝好友请求

        }

    }
}
