package com.gdeer.gdtesthub.view.decorview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class FakeNotiHelper {
    private long mKeepTime = 3000L;
    private ViewGroup mContainer;
    private int mWidth;
    private int mHeight;
    private boolean mShouldStop;
    private final ObjectAnimator mAniShow;
    private final ObjectAnimator mAniHide;

    private Handler mHandler = new Handler();
    private Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    public FakeNotiHelper(Context context) {
        mContainer = new SwipeView(context);
        ((SwipeView) mContainer).setSwipeListener(new SwipeView.SwipeListener() {
            @Override
            public void onDown() {
                mShouldStop = true;
                mHandler.removeCallbacksAndMessages(null);
            }

            @Override
            public void onUpSwipe() {
                hide();
            }

            @Override
            public void onUpNoSwipe() {

            }
        });

        mAniShow = ObjectAnimator.ofFloat(mContainer, "TranslationY", 0).setDuration(200);
        mAniShow.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mHandler.postDelayed(mHideRunnable, mKeepTime);
            }
        });
        mAniShow.addUpdateListener(animation -> {
            if (mShouldStop) {
                animation.cancel();
            }
        });

        mAniHide = ObjectAnimator.ofFloat(mContainer, "TranslationY", 0).setDuration(200);
        mAniHide.addUpdateListener(animation -> {
            if (mShouldStop) {
                animation.cancel();
            }
        });
    }

    public void setContentView(View view) {
        if (view != null && view.getParent() == null) {
            mContainer.addView(view);
            initWH();
        }
    }

    public void setKeepTime(long keepTime) {
        mKeepTime = keepTime;
    }

    public void attach(Activity activity) {
        if (activity != null && mContainer.getParent() == null) {
            ViewGroup parent = activity.findViewById(android.R.id.content);
            if (parent != null) {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                parent.addView(mContainer, params);
            }
        }
    }

    public void attach(ViewGroup viewGroup) {
        if (viewGroup != null && mContainer.getParent() == null) {
            viewGroup.addView(mContainer, 0);
        }
    }

    public void show() {
        mShouldStop = false;
        if (mHeight != 0) {
            mAniShow.setFloatValues(mContainer.getTranslationY(), 0);
            mAniShow.start();
        }
    }

    public void hide() {
        mShouldStop = false;
        if (mHeight != 0) {
            Log.d("zhangjl", "mContainer.getTranslationY() " + mContainer.getTranslationY());
            mAniHide.setFloatValues(mContainer.getTranslationY(), -mHeight);
            mAniHide.start();
        }
    }

    private void initWH() {
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        mContainer.measure(widthMeasureSpec, heightMeasureSpec);

        mWidth = mContainer.getMeasuredWidth();
        mHeight = mContainer.getMeasuredHeight();

        mContainer.setTranslationY(-mHeight);
        Log.d("zhangjl", "mHeight " + mHeight);
    }

    public void stop() {
        mShouldStop = true;
    }
}
