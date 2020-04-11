package com.gdeer.gdtesthub.view.decorview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class SwipeView extends FrameLayout {

    private float mDownY;
    private SwipeListener mSwipeListener;

    public SwipeView(Context context) {
        super(context, null);
    }

    public SwipeView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public SwipeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSwipeListener(SwipeListener swipeListener) {
        mSwipeListener = swipeListener;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("zhangjl", "onInterceptTouchEvent");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("zhangjl", "dispatchTouchEvent " + event.getAction());
        if (mSwipeListener == null) {
            return super.dispatchTouchEvent(event);
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mSwipeListener.onDown();
            mDownY = event.getY();
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float curY = event.getY();
            if (mDownY - curY > 20) {
                mSwipeListener.onUpSwipe();
            } else {
                mSwipeListener.onUpNoSwipe();
            }
        }
        return super.dispatchTouchEvent(event);
    }

    interface SwipeListener {
        void onDown();

        void onUpSwipe();

        void onUpNoSwipe();
    }
}
