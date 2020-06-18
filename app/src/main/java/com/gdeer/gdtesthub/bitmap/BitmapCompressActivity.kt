package com.gdeer.gdtesthub.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_bitmap_compress.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


class BitmapCompressActivity : AppCompatActivity() {

    var drawableId = R.drawable.sun_png8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bitmap_compress)

        btn_origin.setOnClickListener {
            origin()
        }

        btn_quality.setOnClickListener {
            quality()
        }

        btn_options.setOnClickListener {
            options()
        }

        btn_matrix.setOnClickListener {
            matrix()
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
        imageView3.setImageBitmap(bm)
        log(bm, "压缩前")
    }

    fun quality() {
        val bmRaw = BitmapFactory.decodeResource(resources, drawableId, null)

        val baos = ByteArrayOutputStream()
        // 质量压缩方法,这里100表示不压缩,把压缩后的数据存放到baos中
        bmRaw.compress(Bitmap.CompressFormat.JPEG, 50, baos)
        val bais = ByteArrayInputStream(baos.toByteArray())
        val bmScaled = BitmapFactory.decodeStream(bais, null, null)
        imageView3.setImageBitmap(bmScaled)
        log(bmScaled)

        val bytesLength = bitmapToByteArray(bmRaw)?.size
        Log.d("zhangjl", "压缩前 bytesLength:$bytesLength")
        Log.d("zhangjl", "压缩后 bytesLength:${baos.toByteArray().size}")
    }

    private fun options() {
        val options = BitmapFactory.Options()
        options.inSampleSize = 2
        val bmScaled = BitmapFactory.decodeResource(resources, drawableId, options)
        imageView3.setImageBitmap(bmScaled)
        log(bmScaled)
    }

    private fun matrix() {
        val bmRaw = BitmapFactory.decodeResource(resources, drawableId, null)
        val matrix = Matrix()
        matrix.setScale(0.5f, 0.5f)
        val bmScaled = Bitmap.createBitmap(bmRaw, 0, 0, bmRaw.width, bmRaw.height, matrix, true)
        imageView3.setImageBitmap(bmScaled)
        log(bmScaled)
    }

    fun rgb565() {
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.RGB_565
        val bmNew = BitmapFactory.decodeResource(resources, drawableId, options)
        imageView3.setImageBitmap(bmNew)
        log(bmNew)
    }

    private fun rgb565Copy() {
        val bmRaw = BitmapFactory.decodeResource(resources, drawableId, null)
        val bmScaled = bmRaw.copy(Bitmap.Config.RGB_565, true)
        imageView3.setImageBitmap(bmScaled)
        log(bmScaled)
    }

    fun log(bm: Bitmap?, prefix: String = "压缩后") {
        bm?.apply {
            Log.i("zhangjl", "${prefix}图片大小 ${bm.byteCount / 1024}K " +
                    "宽度:${bm.width} " +
                    "高度:${bm.height} "
            )
        }
    }

    private fun bitmapToByteArray(bm: Bitmap): ByteArray? {
        val baos = ByteArrayOutputStream()
        // 质量压缩方法,这里100表示不压缩,把压缩后的数据存放到baos中
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
        return baos.toByteArray()
    }
}
