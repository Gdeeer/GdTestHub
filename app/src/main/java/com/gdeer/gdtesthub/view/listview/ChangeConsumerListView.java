package com.gdeer.gdtesthub.view.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.gdeer.gdtesthub.reflect.FieldUtils;
import com.gdeer.gdtesthub.reflect.MethodUtils;

/**
 * 可改变触摸事件消费者的 ListView
 */
public class ChangeConsumerListView extends ListView {
    private String mTag;
    private boolean mNeedSuperIntercept = true;

    private MotionEvent mLastEvent;

    private SwipeListener mSwipeListener;

    public ChangeConsumerListView(Context context) {
        super(context);
    }

    public ChangeConsumerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChangeConsumerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTagName(String mTag) {
        this.mTag = mTag;
    }

    public void setSwipeListener(SwipeListener swipeListener) {
        this.mSwipeListener = swipeListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev == null) {
            return false;
        }

        String actionStr = MotionEvent.actionToString(ev.getAction());
        Log.d("zhangjl", mTag + " dispatchTouchEvent() called with: " + actionStr
            + " firstTarget: " + getFirstTouchTarget()
            + " touchMode: " + getTouchMode()
            + " disallowIntercept: " + isDisallowIntercept()
        );

        boolean controllerHasChanged = false;
        if (mLastEvent != null) {
            float lastY = mLastEvent.getY();
            float disY = ev.getY() - lastY;
            if (Math.abs(disY) > 0 && mSwipeListener != null) {
                if (disY > 0) {
                    controllerHasChanged = swipeDown();
                } else {
                    controllerHasChanged = swipeUp();
                }
            }
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN || ev.getAction() == MotionEvent.ACTION_MOVE) {
            mLastEvent = MotionEvent.obtain(ev);
        } else {
            mLastEvent = null;
        }
        boolean result;
        if (controllerHasChanged) {
            result = dispatchNewDownTouchEvent(ev);
        } else {
            result = super.dispatchTouchEvent(ev);
        }
        Log.d("zhangjl", mTag + " dispatchTouchEvent() result: " + result);
        return result;
    }

    private boolean swipeDown() {
        Log.d("zhangjl", "swipeDown parentReachBottom: " + isReachBottom()
            + " childReachTop: " + mSwipeListener.isChildListReachTop()
            + " needSuper: " + mNeedSuperIntercept);
        if (mSwipeListener != null && mSwipeListener.isChildListReachTop() && !mNeedSuperIntercept) {
            mNeedSuperIntercept = true;
            return true;
        }
        return false;
    }

    private boolean swipeUp() {
        Log.d("zhangjl", "swipeUp parentReachBottom: " + isReachBottom()
            + " childReachTop: " + mSwipeListener.isChildListReachTop()
            + " needSuper: " + mNeedSuperIntercept);
        if (isReachBottom() && mNeedSuperIntercept) {
            mNeedSuperIntercept = false;
            return true;
        }
        return false;
    }

    private boolean dispatchNewDownTouchEvent(MotionEvent ev) {
        if (ev != null) {
            ev.setAction(MotionEvent.ACTION_DOWN);
            return dispatchTouchEvent(ev);
        }
        return false;
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

    /**
     * 另一种判断触顶的方法
     */
    private boolean isReachTop2() {
        return !canScrollVertically(-1);
    }

    public boolean isReachBottom() {
        boolean result = false;
        if (getLastVisiblePosition() == getCount() - 1) {
            final View bottomChildView = getChildAt(getLastVisiblePosition() - getFirstVisiblePosition());
            result = (getHeight() == bottomChildView.getBottom());
        }
        return result;
    }

    interface SwipeListener {
        boolean isChildListReachTop();
    }
}
