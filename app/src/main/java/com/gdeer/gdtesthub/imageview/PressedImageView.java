package com.gdeer.gdtesthub.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 按压后可改变透明度、添加一层颜色的 ImageView
 */
public class PressedImageView extends ImageView {
    public PressedImageView(Context context) {
        this(context, null);
    }

    public PressedImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PressedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTouchListener();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initTouchListener() {
        setOnTouchListener((v, event) -> {
//            addColorLayer(event);
            changeAlpha(event);
            return false;
        });
    }

    /**
     * 加一层颜色
     */
    private void addColorLayer(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 如果没有 listener，不消耗 down，up 不会交给它
                if (hasOnClickListeners()) {
                    // 给 ImageView 的非透明区域蒙上一层颜色，不能改变原图的透明度
                    setColorFilter(Color.parseColor("#abffffff"));
                }
                break;
            case MotionEvent.ACTION_UP:
                setColorFilter(null);
                break;
            default:
                break;
        }
    }

    /**
     * 改变透明度
     */
    private void changeAlpha(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // 如果没有 listener，不消耗 down，up 不会交给它
                if (hasOnClickListeners()) {
                    setAlpha(0.1f);
                }
                break;
            case MotionEvent.ACTION_UP:
                setAlpha(1f);
                break;
            default:
                break;
        }
    }
}
