package com.gdeer.gdtesthub.touchevent.multitouch;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MultiTouchLayout extends View {

    public MultiTouchLayout(@NonNull Context context) {
        super(context);
    }

    public MultiTouchLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiTouchLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 多点触控
     * 使用 getActionMasked()
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("zhangjl", "第一根手指 down");
                return true;
            case MotionEvent.ACTION_MOVE:
//                Log.d("zhangjl", "move");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("zhangjl", "最后一根手指 up");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                int indexDown = event.getActionIndex();
                int idDown = event.getPointerId(indexDown);
                Log.d("zhangjl", "有一根手指 down, id: " + idDown + " index: " + indexDown);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                int indexUp = event.getActionIndex();
                int idUp = event.getPointerId(indexUp);
                Log.d("zhangjl", "有一根手指 up, id: " + idUp + " index: " + indexUp);
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
