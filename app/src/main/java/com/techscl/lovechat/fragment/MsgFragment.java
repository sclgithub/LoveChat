package com.techscl.lovechat.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.easemob.chat.EMConversation;
import com.techscl.lovechat.R;
import com.techscl.lovechat.customview.SlideCutListView;
import com.techscl.lovechat.adapter.MsgListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 15/8/27.
 * hah
 */
public class MsgFragment extends Fragment {
    private InputMethodManager inputMethodManager;
    private SlideCutListView msg_list;
    private MsgListAdapter msgListAdapter;
    public LinearLayout msg_layout;
    private boolean hidden;
    private List<EMConversation> conversationList = new ArrayList<>();
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
        if (savedInstanceState!=null&&savedInstanceState.getBoolean("isConflict",false))
            return;
        inputMethodManager= (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        msg_layout= (LinearLayout) getView().findViewById(R.id.msg_layout);
        msg_list= (SlideCutListView) getView().findViewById(R.id.msg_list);
    }

    /**
     * 页面初始化
     *
     * @param view 当前页面
     */
    private void initView(View view) {
        msg_list = (SlideCutListView) view.findViewById(R.id.msg_list);

    }
}
