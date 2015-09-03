package com.techscl.lovechat.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TableRow;

import com.easemob.chat.EMChatManager;
import com.techscl.lovechat.R;
import com.techscl.lovechat.activity.LoginActivity;
import com.techscl.lovechat.activity.ModificationActivity;
import com.techscl.lovechat.activity.SettingsActivity;
import com.techscl.lovechat.activity.StartActivity;

/**
 * Created by 宋春麟 on 15/8/27.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    private Button logout, modification;
    private ImageView user_img;
    private TableRow setting_table;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_me, null);

        initView(view);

        return view;

    }

    private void initView(View view) {
        logout = (Button) view.findViewById(R.id.logout);
        modification = (Button) view.findViewById(R.id.modification);
        user_img = (ImageView) view.findViewById(R.id.user_img);
        setting_table = (TableRow) view.findViewById(R.id.setting_table);
        logout.setOnClickListener(this);
        modification.setOnClickListener(this);
        user_img.setOnClickListener(this);
        setting_table.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout:
                showPopupWindow();
                break;
            case R.id.modification:
                Intent modification = new Intent(getActivity(), ModificationActivity.class);
                startActivity(modification);
                break;
            case R.id.user_img:

                break;
            case R.id.setting_table:
                Intent setting_table = new Intent(getActivity(), SettingsActivity.class);
                startActivity(setting_table);
                break;
            default:
                break;
        }
    }

    /**
     * 显示popupWindow
     */
    private void showPopupWindow() {
        // 利用layoutInflater获得View
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.logout_window, null);

        // 下面是两种方法得到宽度和高度 getWindow().getDecorView().getWidth()

        final PopupWindow window = new PopupWindow(view,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);


        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);


        // 设置popWindow的显示和消失动画
        window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(getActivity().findViewById(R.id.logout),
                Gravity.BOTTOM, 0, 0);

        // 这里检验popWindow里的button是否可以点击
        Button cancel_btn = (Button) view.findViewById(R.id.cancel_btn);
        Button logout_btn = (Button) view.findViewById(R.id.logout_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartActivity.editor = StartActivity.preferences.edit();
                StartActivity.editor.putBoolean("login", true);
                StartActivity.editor.commit();
                EMChatManager.getInstance().logout();//此方法为同步方法
                Intent logout = new Intent(getActivity(), LoginActivity.class);
                startActivity(logout);
                getActivity().finish();
            }
        });
        //popWindow消失监听方法
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

    }

}
