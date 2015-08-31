package com.techscl.lovechat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.techscl.lovechat.R;
import com.techscl.lovechat.adapter.MainFragmentAdapter;
import com.techscl.lovechat.fragment.FindFragment;
import com.techscl.lovechat.fragment.FriendsFragment;
import com.techscl.lovechat.fragment.MeFragment;
import com.techscl.lovechat.fragment.MsgFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;// 定义toolbar
    private ViewPager viewpager_main;// 定义viewpager
    private TabLayout tabLayout;// 定义tabLayout
    private MainFragmentAdapter fragmentAdapter;// 定义适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 设置透明状态栏

        initView();

    }

    /**
     * 页面初始化
     */
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewpager_main = (ViewPager) findViewById(R.id.viewpager_main);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        toolbar.setTitle("LoveChat");// 设置toolbar标题
        setSupportActionBar(toolbar);// 可通过actionBar方法设置toolbar

        List<Fragment> views = new ArrayList<>();// 页面列表

        views.add(new MsgFragment());
        views.add(new FriendsFragment());
        views.add(new FindFragment());
        views.add(new MeFragment());

        String[] tabs = getResources().getStringArray(R.array.tab);// 标签字符串数组

        fragmentAdapter = new MainFragmentAdapter(getSupportFragmentManager(), views, tabs);// 实例化适配器
        viewpager_main.setAdapter(fragmentAdapter);// 绑定适配器
        tabLayout.setTabsFromPagerAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewpager_main);// 联动
        // 设置图标来自自定义组件
        tabLayout.getTabAt(0).setCustomView(R.layout.tab_msg);
        tabLayout.getTabAt(1).setCustomView(R.layout.tab_friends);
        tabLayout.getTabAt(2).setCustomView(R.layout.tab_find);
        tabLayout.getTabAt(3).setCustomView(R.layout.tab_me);
        tabLayout.getTabCount();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent setting = new Intent(this, SettingsActivity.class);
            startActivity(setting);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
