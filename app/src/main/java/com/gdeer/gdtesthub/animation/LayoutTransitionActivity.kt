package com.gdeer.gdtesthub.animation

import android.animation.AnimatorSet
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_layout_transition_2.*

class LayoutTransitionActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_transition_2)

        val transition = LayoutTransition()
        // 有 item 出现时，出现的那个 item 的动画
        transition.setDuration(LayoutTransition.APPEARING, 2000L)
        // 有 item 出现时，其他 item 的动画
        transition.setDuration(LayoutTransition.CHANGE_APPEARING, 200L)
        // 有 item 隐藏时，隐藏的那个 item 的动画
        transition.setDuration(LayoutTransition.DISAPPEARING, 200L)
        // 有 item 隐藏时，其他 item 的动画
        transition.setDuration(LayoutTransition.CHANGE_DISAPPEARING, 2000L)
//        root_layout_transition.layoutTransition = transition

        tv_transition_1.setOnClickListener {
//            tv_transition_2.visibility = View.VISIBLE
            buildFadeInAnimation(tv_transition_2, 100, 0).start()
        }

        tv_transition_2.setOnClickListener {
//            tv_transition_2.visibility = View.GONE
            buildFadeOutAnimation(tv_transition_2, 100, 0).start()
        }
    }

    private fun buildFadeOutAnimation(view: View, duration: Int, delay: Int): AnimatorSet {
        val animatorSet = AnimatorSet()

        val translationY = ObjectAnimator.ofFloat(view, "translationY", 0f, -200f)
        translationY.duration = duration.toLong()

        val alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        alphaAnim.duration = duration.toLong()
        alphaAnim.addUpdateListener { animation ->
            root_layout_transition.requestLayout()
        }

        animatorSet.playTogether(translationY, alphaAnim)
        animatorSet.interpolator = DecelerateInterpolator(2f)
        animatorSet.duration = duration.toLong()
        animatorSet.startDelay = delay.toLong()
        return animatorSet
    }

    private fun buildFadeInAnimation(view: View, duration: Int, delay: Int): AnimatorSet {
        val animatorSet = AnimatorSet()

        val translationY = ObjectAnimator.ofFloat(view, "translationY", -200f, 0f)
        translationY.duration = duration.toLong()

        val alphaAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        alphaAnim.duration = duration.toLong()

        animatorSet.playTogether(translationY, alphaAnim)
        animatorSet.interpolator = DecelerateInterpolator(2f)
        animatorSet.duration = duration.toLong()
        animatorSet.startDelay = delay.toLong()
        return animatorSet
    }
}
