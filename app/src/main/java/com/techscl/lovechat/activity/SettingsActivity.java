package com.techscl.lovechat.activity;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.techscl.lovechat.R;

/**
 * Created by dllo on 15/8/28.
 */
public class SettingsActivity extends PreferenceActivity implements View.OnClickListener, GestureDetector.OnGestureListener {
    private PreferenceScreen preferenceScreen;
    private ImageView back;
    private LinearLayout layout_set;
    private GestureDetector gestureDetector;
    private int verticalMinDistance = 30;
    private int middleDistance = 150;
    private int minVelocity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 设置透明状态栏

        addPreferencesFromResource(R.xml.setting);

        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        back = (ImageView) findViewById(R.id.back);
        layout_set = (LinearLayout) findViewById(R.id.layout_set);
        gestureDetector = new GestureDetector(this);
        back.setOnClickListener(this);
    }

    /**
     * 点击事件监听
     *
     * @param view 点击的控件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    /**
     * 长按
     *
     * @param motionEvent
     */
    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    /**
     * 快速划过
     *
     * @param motionEvent
     * @param motionEvent1
     * @param v
     * @param v1
     * @return
     */
    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (motionEvent1.getX() - motionEvent.getX() > verticalMinDistance && Math.abs(v) > minVelocity && (Math.abs(motionEvent1.getY() - motionEvent.getY()) < middleDistance)) {
            finish();
        } else if (motionEvent.getX() - motionEvent1.getX() > verticalMinDistance && Math.abs(v) > minVelocity && (Math.abs(motionEvent1.getY() - motionEvent.getY()) < middleDistance)) {

        }
        return false;
    }

    /**
     * 手势添加到触摸事件
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
}
