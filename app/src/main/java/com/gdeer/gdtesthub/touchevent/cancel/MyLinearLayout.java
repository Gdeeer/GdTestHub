package com.gdeer.gdtesthub.touchevent.cancel;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

    private static final int MAX_MOVE_COUNT = 15;

    private int moveCount;

    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 子 view 收到 down 后，父容器拦截后续的某个 move 或 up，子 view 会收到 cancel （再后续的 move、up 就收不到了）
     *
     * 要在 onInterceptTouchEvent 中拦截，不能在 dispatchTouchEvent 中，
     * 因为 cancel 就是在 super.dispatchTouchEvent() 中发送的
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (moveCount == MAX_MOVE_COUNT) {
                Log.d("zhangjl", "parent intercept move");
                return true;
            }
            moveCount++;
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            Log.d("zhangjl", "parent intercept up");
            return true;
        }
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            moveCount = 0;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
