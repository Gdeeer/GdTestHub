package com.gdeer.gdtesthub.animation

import android.animation.ValueAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_change_height.*

/**
 * 动态改变一个 button 的高度
 *
 * ConstrainLayout 内的 View 高度设置 0dp
 * 其实是 match_constraints，View 不会消失
 * 对 Button 和一个纯 View 来说，效果不一
 * todo 区别原因
 */
class ChangeHeightActivity : AppCompatActivity() {

    lateinit var mHideAnimator: ValueAnimator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_height)

        // 将一个 button 的高度变为 0，变成的 0 的时候会恢复它原始宽高
        // 因为它的父容器是 ConstraintLayout
        btn_animation_test_trigger.setOnClickListener {
            mHideAnimator = ValueAnimator.ofInt(btn_animation_test_content.height, 0)
            mHideAnimator.addUpdateListener { valueAnimator ->
                Log.d("zhangjl", "" + valueAnimator.animatedValue)
                btn_animation_test_content.layoutParams.height = valueAnimator.animatedValue as Int
                btn_animation_test_content.layoutParams.height = 0
                btn_animation_test_content.requestLayout()
            }
            mHideAnimator.duration = 1000
            mHideAnimator.start()
        }
    }
}
