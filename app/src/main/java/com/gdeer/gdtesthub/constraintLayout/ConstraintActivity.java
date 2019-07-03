package com.gdeer.gdtesthub.constraintLayout;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
    }

    /**
     * 约束变换的帧动画
     */
    void animateToKeyframeTwo() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.load(this, R.layout.keyframe_two);
        TransitionManager.beginDelayedTransition(mBinding.consRoot);
        constraintSet.applyTo(mBinding.consRoot);
    }
}
