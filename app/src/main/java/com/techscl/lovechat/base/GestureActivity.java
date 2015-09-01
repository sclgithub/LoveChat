package com.techscl.lovechat.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.techscl.lovechat.utils.To;

/**
 * Created by 宋春麟 on 15/8/28.
 */
public class GestureActivity extends ActionBarActivity implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private GestureDetector gestureDetector;
    private int verticalMinDistance = 30;
    private int middleDistance = 150;
    private int minVelocity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gestureDetector = new GestureDetector(this);
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

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        if (motionEvent1.getX() - motionEvent.getX() > verticalMinDistance && Math.abs(v) > minVelocity && (Math.abs(motionEvent1.getY() - motionEvent.getY()) < middleDistance)) {
            finish();
        } else if (motionEvent.getX() - motionEvent1.getX() > verticalMinDistance && Math.abs(v) > minVelocity && (Math.abs(motionEvent1.getY() - motionEvent.getY()) < middleDistance)) {
            To.showShort(this, "左滑");
        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        gestureDetector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
}
