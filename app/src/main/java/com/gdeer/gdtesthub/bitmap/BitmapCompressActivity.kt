package com.gdeer.gdtesthub.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_bitmap_compress.*


class BitmapCompressActivity : AppCompatActivity() {

    var drawableId = R.drawable.sun_png8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_compress)

        btn_origin.setOnClickListener {
            origin()
        }

        btn_quality.setOnClickListener {
            // todo
        }

        btn_options.setOnClickListener {
            // todo
        }

        btn_matrix.setOnClickListener {
            // todo
        }

        btn_rgb565.setOnClickListener {
            rgb565()
        }

        btn_rgb565_copy.setOnClickListener {
            rgb565Copy()
        }

        btn_png8.setOnClickListener {
            drawableId = R.drawable.sun_png8
        }

        btn_png24.setOnClickListener {
            drawableId = R.drawable.panda_png24
        }

        btn_png32.setOnClickListener {
            drawableId = R.drawable.earth_png32
        }

        btn_jpg.setOnClickListener {
            drawableId = R.drawable.girl
        }
    }

    fun origin() {
        val bm = BitmapFactory.decodeResource(resources, drawableId, null)
        log(bm, "压缩前")
        imageView3.setImageBitmap(bm)
    }

    fun rgb565() {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565

        val bm = BitmapFactory.decodeResource(resources, drawableId, options)
        log(bm)
        imageView3.setImageBitmap(bm)
    }

    private fun rgb565Copy() {
        val bm = BitmapFactory.decodeResource(resources, drawableId, null)
        val bm1 = bm.copy(Bitmap.Config.RGB_565, true)
        log(bm1)
        imageView3.setImageBitmap(bm1)
    }

    fun log(bm: Bitmap, prefix: String = "压缩后") {
        Log.i("zhangjl", "${prefix}图片大小 ${bm.byteCount / 1024}K " +
                " 宽度为 ${bm.width}  高度为 ${bm.height}")
    }
}
