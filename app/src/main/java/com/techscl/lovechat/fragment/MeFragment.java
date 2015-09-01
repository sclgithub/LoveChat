package com.techscl.lovechat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;

import com.techscl.lovechat.R;
import com.techscl.lovechat.activity.LoginActivity;
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
                StartActivity.editor = StartActivity.preferences.edit();
                StartActivity.editor.putBoolean("login", true);
                StartActivity.editor.commit();

                Intent logout = new Intent(getActivity(), LoginActivity.class);
                startActivity(logout);
                getActivity().finish();

                break;
            case R.id.modification:

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
}
