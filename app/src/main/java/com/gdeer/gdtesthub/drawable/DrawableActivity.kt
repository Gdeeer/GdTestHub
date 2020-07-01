package com.gdeer.gdtesthub.drawable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.utils.DeviceUtil
import kotlinx.android.synthetic.main.activity_drawable.*

/**
 * 1080 宽的图，加载到 1080p 的手机上，结果并不是 1080
 * 因为图放在 xxhdpi 目录下，会根据 dpi 缩放
 * 如果放在 nodpi 目录下，就会显示 1080 的像素
 * 如果放在 drawable 目录下，会按屏幕宽高去加载
 */
class DrawableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawable)

        tv_load.post {
            val width = iv_drawable.width
            val height = iv_drawable.height
            tv_load.text = "$width x $height"
        }

        val dpi = DeviceUtil.getDisplaySizeDensityDpi(this)
        tv_dpi.text = dpi.toString()
    }
}
