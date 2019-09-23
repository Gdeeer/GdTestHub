package com.gdeer.gdtesthub.glide

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_bitmap.*

class BitmapActivity : AppCompatActivity() {

    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap)

        btn_ready.setOnClickListener {
            bitmap = Bitmap.createBitmap(1000, 10000, Bitmap.Config.ARGB_8888)
            val canvas: Canvas = Canvas(bitmap)
            canvas.drawColor(Color.MAGENTA)
        }

        btn_glide.setOnClickListener {
            Glide.with(this)
                    .load(bitmap)
                    .into(imageView3)
        }

        btn_origin.setOnClickListener {
            imageView3.setImageBitmap(bitmap)
        }

        btn_clear.setOnClickListener {
            imageView3.setImageBitmap(null)
        }
    }
}
