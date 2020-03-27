package com.gdeer.gdtesthub.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_filter.btn_origin

class FilterActivity : AppCompatActivity() {

    private val scale = 50f

    private val resId = R.drawable.sun_png8_little

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        btn_origin.setOnClickListener {
            iv_filter.setImageResource(resId)
        }

        btn_filter_true.setOnClickListener {
            val bmRaw = BitmapFactory.decodeResource(resources, resId, null)
            val matrix = Matrix()
            matrix.setScale(scale, scale)
            val bmScaled = Bitmap.createBitmap(bmRaw, 0, 0, bmRaw.width, bmRaw.height, matrix, true)
            iv_filter.setImageBitmap(bmScaled)
        }

        btn_filter_false.setOnClickListener {
            val bmRaw = BitmapFactory.decodeResource(resources, resId, null)
            val matrix = Matrix()
            matrix.setScale(scale, scale)
            val bmScaled = Bitmap.createBitmap(bmRaw, 0, 0, bmRaw.width, bmRaw.height, matrix, false)
            iv_filter.setImageBitmap(bmScaled)
        }
    }
}
