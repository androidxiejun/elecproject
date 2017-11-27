package com.example.toucheventdemo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by yangjw on 2016/8/31.
 */
public class CustomListView extends ListView {
    private static final String TAG = "androidxx";
    private int startX,startY;
    private View childView;
    private int distanceX;
    private boolean isLocked;

    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                distanceX = (int) (startX - ev.getX());
                Log.i(TAG, "onTouchEvent: " + distanceX);
                if (distanceX > -300 && distanceX < -50) {
                    childView.scrollTo(distanceX,0);
                    isLocked = true;
                }
                break;
            case MotionEvent.ACTION_DOWN:
                if (childView != null) {
                    childView.scrollTo(0,0);
                    isLocked = false;
                }

                startX = (int) ev.getX();
                startY = (int) ev.getY();
                //通过X、Y坐标获取ListView的Postion
                int position = pointToPosition(startX, startY);
                //判断Postion是否有效
                if (position == AdapterView.INVALID_POSITION) {
                    return super.onTouchEvent(ev);
                }
                childView = getChildAt(position - getFirstVisiblePosition());
                childView.setBackgroundColor(Color.BLUE);
                break;
            case MotionEvent.ACTION_UP:
                childView.setBackgroundColor(Color.TRANSPARENT);
                break;
        }
        if (isLocked) {
            return true;
        }
        return super.onTouchEvent(ev);
    }
}
