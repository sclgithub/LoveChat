package com.techscl.lovechat.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactListener;
import com.easemob.chat.EMContactManager;
import com.easemob.exceptions.EaseMobException;
import com.techscl.lovechat.R;
import com.techscl.lovechat.utils.L;
import com.techscl.lovechat.utils.To;

import java.util.List;

/**
 * Created by 宋春麟 on 15/8/27.
 */
public class FriendsFragment extends Fragment implements EMContactListener {
    private ListView friends_list;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friends, null);

        EMChatManager.getInstance().getChatOptions().setUseRoster(true);

        initView(view);

        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> username = EMContactManager.getInstance().getContactUserNames();
                    L.i("好友列表" + username);
                } catch (EaseMobException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;

    }

    /**
     * 页面初始化
     *
     * @param view 当前页
     */
    private void initView(View view) {
        friends_list = (ListView) view.findViewById(R.id.friends_list);
    }


    @Override
    public void onContactAdded(List<String> list) {

    }

    @Override
    public void onContactDeleted(List<String> list) {

    }

    @Override
    public void onContactInvited(String s, String s1) {
        To.showShort(getActivity(), s + s1);
    }

    @Override
    public void onContactAgreed(String s) {

    }

    @Override
    public void onContactRefused(String s) {

    }
}
