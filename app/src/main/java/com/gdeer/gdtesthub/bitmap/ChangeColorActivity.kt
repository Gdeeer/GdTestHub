package com.gdeer.gdtesthub.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_change_color.*

class ChangeColorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_color)

        val bm = BitmapFactory.decodeResource(resources, R.drawable.icon_58, null)
        iv_change_color_origin.setImageBitmap(bm)

        val bm1 = (iv_change_color_origin.drawable as BitmapDrawable).bitmap
        val bm2 = changeBitmap(bm1)
        iv_change_color_target.setImageBitmap(bm2)
    }

    /**
     * 将 bitmap 中的白色像素改为黑色
     */
    private fun changeBitmap(bitmap: Bitmap): Bitmap {
        val bitmapH: Int = bitmap.height
        val bitmapW: Int = bitmap.width
        val arrayColorLength: Int = bitmapH * bitmapW
        val arrayColor = IntArray(arrayColorLength)
        var index = 0
        for (i in 0 until bitmapH) {
            for (j in 0 until bitmapW) {
                // 获得 Bitmap 图片中每一个点的 color 值
                var color = bitmap.getPixel(j, i)
                var r = Color.red(color)
                var g = Color.green(color)
                var b = Color.blue(color)
                var a = Color.alpha(color)
                if (r == 255 && r == g && r == b) {
                    r = 0
                    g = 0
                    b = 0
                }
                color = Color.argb(a, r, g, b)
                arrayColor[index] = color
                index++
            }
        }
        return Bitmap.createBitmap(arrayColor, bitmapW, bitmapH, Bitmap.Config.ARGB_8888)
    }
}
