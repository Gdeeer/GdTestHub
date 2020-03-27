package com.gdeer.gdtesthub.animation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_rotation.*

/**
 * 使一张图片绕中心旋转
 */
class RotationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotation)

        // 属性动画
        val animator = ObjectAnimator.ofFloat(iv_rotation, "rotation", 0f, 360f)
        animator.apply {
            duration = 2000
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
        }

        // View 动画
        // 注意，应使用 6 个参数的构造函数，第三个和第五个参数指明相对于自身
        // 如果使用 4 个参数的构造函数，pivotX、pivotY 是绝对值
        val animation = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        animation.apply {
            duration = 2000
            interpolator = LinearInterpolator()
            repeatCount = Animation.INFINITE
        }

        btn_rotation_animator.setOnClickListener {
            if (animator.isRunning) {
                animator.end()
            } else {
                animator.start()
            }
        }

        btn_rotation_animation.setOnClickListener {
            iv_rotation.clearAnimation()
            iv_rotation.startAnimation(animation)
        }
    }
}
