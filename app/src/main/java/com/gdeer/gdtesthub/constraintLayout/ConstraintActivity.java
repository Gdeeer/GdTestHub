package com.gdeer.gdtesthub.constraintLayout;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.databinding.ActivityConstraintBinding;

public class ConstraintActivity extends AppCompatActivity {

    private ActivityConstraintBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_constraint);

        mBinding.button2.setOnClickListener(v -> {
            animateToKeyframeTwo();
        });

        mBinding.btnLayerTest.setOnClickListener(v -> {
            // layer 的使用
            mBinding.layer.setRotation(30);
        });

        // 测试 ConstrainLayout 是否可以容纳超过自身宽高的 View
        mBinding.clMoveTest.setScrollY(60);
    }

    /**
     * 约束变换的帧动画
     * <p>
     * 将 button 移至页面底部、并改变宽高
     * 怎么设置时间？
     */
    void animateToKeyframeTwo() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.load(this, R.layout.keyframe_two);
        TransitionManager.beginDelayedTransition(mBinding.consRoot);
        constraintSet.applyTo(mBinding.consRoot);
    }
}
