package com.gdeer.gdtesthub.animation

import android.animation.LayoutTransition
import android.app.Activity
import android.os.Bundle
import android.view.View
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_layout_transition.*

class LayoutTransitionActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_transition)

        val transition = LayoutTransition()
        // 有 item 出现时，出现的那个 item 的动画
        transition.setDuration(LayoutTransition.APPEARING, 2000L)
        // 有 item 出现时，其他 item 的动画
        transition.setDuration(LayoutTransition.CHANGE_APPEARING, 200L)
        // 有 item 隐藏时，隐藏的那个 item 的动画
        transition.setDuration(LayoutTransition.DISAPPEARING, 200L)
        // 有 item 隐藏时，其他 item 的动画
        transition.setDuration(LayoutTransition.CHANGE_DISAPPEARING, 2000L)
        root_layout_transition.layoutTransition = transition

        tv_transition_1.setOnClickListener {
            tv_transition_2.visibility = View.VISIBLE
        }

        tv_transition_2.setOnClickListener {
            tv_transition_2.visibility = View.GONE
        }
    }
}
