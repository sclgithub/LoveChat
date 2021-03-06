package com.techscl.lovechat.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.easemob.EMConnectionListener;
import com.easemob.chat.EMChat;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactListener;
import com.easemob.chat.EMContactManager;
import com.easemob.chat.EMGroupManager;
import com.techscl.lovechat.R;
import com.techscl.lovechat.adapter.MainFragmentAdapter;
import com.techscl.lovechat.db.sqlite.SQLiteDataBaseTools;
import com.techscl.lovechat.fragment.FindFragment;
import com.techscl.lovechat.fragment.FriendsFragment;
import com.techscl.lovechat.fragment.MeFragment;
import com.techscl.lovechat.fragment.MsgFragment;
import com.techscl.lovechat.receiver.NewMessageBroadcastReceiver;
import com.techscl.lovechat.utils.L;
import com.techscl.lovechat.utils.To;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {
    public static RequestQueue requestQueue;
    public static SQLiteDataBaseTools sqLiteDataBaseTools;
    public static ImageLoader imageLoader;
    private Toolbar toolbar;// 定义toolbar
    private ViewPager viewpager_main;// 定义viewpager
    private TabLayout tabLayout;// 定义tabLayout
    private MainFragmentAdapter fragmentAdapter;// 定义适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewMessageBroadcastReceiver msgReceiver = new NewMessageBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(EMChatManager.getInstance().getNewMessageBroadcastAction());
        intentFilter.setPriority(3);
        registerReceiver(msgReceiver, intentFilter);
        requestQueue = Volley.newRequestQueue(this);
        /**
         * 获取手机DPI,手机屏幕宽度和高度
         */
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        L.i("width:" + displayMetrics.widthPixels + "像素");
        L.i("height:" + displayMetrics.heightPixels + "像素");
        L.i("dpi:" + displayMetrics.densityDpi);
        L.i("density:" + displayMetrics.density);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 设置透明状态栏

        initView();

        EMChatManager.getInstance().getChatOptions().setUseRoster(true);

        EMGroupManager.getInstance().loadAllGroups();

        EMChatManager.getInstance().loadAllConversations();

        EMContactManager.getInstance().setContactListener(new MyContactListener());

        EMChatManager.getInstance().addConnectionListener(new EMConnectionListener() {
            @Override
            public void onConnected() {

            }

            @Override
            public void onDisconnected(int i) {

            }
        });
        EMChat.getInstance().setAppInited();
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

    /**
     * 加载菜单布局
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 菜单项监听
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                Intent setting = new Intent(this, SettingsActivity.class);
                startActivity(setting);
                break;
            case R.id.add_friend:
                Intent add_friend = new Intent(this, AddFriendActivity.class);
                startActivity(add_friend);
                break;
            case R.id.scanner_code:
                Intent scanner_code = new Intent(this, CodeScanActivity.class);
                startActivity(scanner_code);
                break;
        }

        return super.onOptionsItemSelected(item);
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
            L.i(username);
        }

        @Override
        public void onContactRefused(String username) {
            // 拒绝好友请求

        }

    }

}
