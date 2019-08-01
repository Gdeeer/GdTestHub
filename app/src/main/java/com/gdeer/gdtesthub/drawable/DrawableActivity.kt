package com.gdeer.gdtesthub.drawable

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.utils.DeviceUtil
import kotlinx.android.synthetic.main.activity_drawable.*

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
