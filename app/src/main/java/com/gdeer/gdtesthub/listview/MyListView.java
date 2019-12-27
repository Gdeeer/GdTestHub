package com.gdeer.gdtesthub.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.gdeer.gdtesthub.reflect.FieldUtils;
import com.gdeer.gdtesthub.reflect.MethodUtils;

public class MyListView extends ListView {
    private static final int DIRECTION_UP = 1;
    private static final int DIRECTION_DOWN = 2;

    private String mTag;
    private boolean mNeedSuperIntercept = true;
    private boolean mNewsOnTop;
    private boolean mIsInReadState;

    private int mSwipeDirection;
    private MotionEvent mLastEvent;

    private SwipeListener mSwipeListener;

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTagName(String mTag) {
        this.mTag = mTag;
    }

    public boolean isNeedSuperIntercept() {
        return mNeedSuperIntercept;
    }

    public void setNeedSuperIntercept(boolean mNeedSuperIntercept) {
        this.mNeedSuperIntercept = mNeedSuperIntercept;
    }

    public void setSwipeListener(SwipeListener mSwipeListener) {
        this.mSwipeListener = mSwipeListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        String actionStr = MotionEvent.actionToString(ev.getAction());
        Log.d("zhangjl", mTag + " dispatchTouchEvent() called with: " + actionStr
            + " firstTarget: " + getFirstTouchTarget()
            + " touchMode: " + getTouchMode()
            + " disallowIntercept: " + isDisallowIntercept()
        );

        if (mLastEvent != null) {
            float lastY = mLastEvent.getY();
            float disY = ev.getY() - lastY;
            if (Math.abs(disY) > 8 && mSwipeListener != null) {
                if (disY > 0) {
                    mSwipeListener.onSwipeDown();
                } else {
                    mSwipeListener.onSwipeUp();
                }
            }
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN || ev.getAction() == MotionEvent.ACTION_MOVE) {
            mLastEvent = MotionEvent.obtain(ev);
        } else {
            mLastEvent = null;
        }
        boolean result = super.dispatchTouchEvent(ev);
        Log.d("zhangjl", mTag + " dispatchTouchEvent() result: " + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        String actionStr = MotionEvent.actionToString(ev.getAction());
        Log.d("zhangjl", mTag + " onInterceptTouchEvent() called with: " + actionStr
            + " firstTarget: " + getFirstTouchTarget()
            + " touchMode: " + getTouchMode()
            + " isDetaching: " + getIsDetaching()
            + " disallowIntercept: " + isDisallowIntercept()
        );

        boolean result = mNeedSuperIntercept && super.onInterceptTouchEvent(ev);
        Log.d("zhangjl", mTag + " onInterceptTouchEvent() result: " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        String actionStr = MotionEvent.actionToString(ev.getAction());
        Log.d("zhangjl", mTag + " onTouchEvent() called with: " + actionStr
            + " firstTarget: " + getFirstTouchTarget()
            + " touchMode: " + getTouchMode()
            + " disallowIntercept: " + isDisallowIntercept()
        );
        boolean result = super.onTouchEvent(ev);
        Log.d("zhangjl", mTag + " onTouchEvent() result: " + result);
        return result;
    }

//    @Override
//    public boolean startNestedScroll(int axes) {
//        Log.d("zhangjl", "startNestedScroll() called with: " + axes);
//        boolean result = super.startNestedScroll(axes);
//        Log.d("zhangjl", "startNestedScroll() result: " + result);
//        return result;
//    }

    public Object getFirstTouchTarget() {
        try {
            return FieldUtils.readField(this, "mFirstTouchTarget");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getTouchMode() {
        try {
            return FieldUtils.readField(this, "mTouchMode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean getIsDetaching() {
        try {
            return (boolean) FieldUtils.readField(this, "mIsDetaching");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isDisallowIntercept() {
        try {
            int mGroupFlags = (int) FieldUtils.readField(this, "mGroupFlags");
            return (mGroupFlags & 0x80000) != 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int invokeFindMotionRow(int y) {
        try {
            return (int) MethodUtils.invokeMethod(this, "findMotionRow", y);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -100;
    }

    public boolean isReachTop() {
        boolean result = false;
        if (getFirstVisiblePosition() == 0) {
            View topChildView = getChildAt(0);
            if (topChildView != null) {
                result = topChildView.getTop() == 0;
            }
        }
        return result;
    }

    interface SwipeListener {
        void onSwipeUp();

        void onSwipeDown();
    }
}
