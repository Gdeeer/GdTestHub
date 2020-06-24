package com.gdeer.gdtesthub.bitmap

import android.graphics.*
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.utils.toBitmap
import kotlinx.android.synthetic.main.activity_porter_duff.*


class PorterDuffActivity : AppCompatActivity() {

    private val ovalBitmap
        get() = resources.getDrawable(R.drawable.shape_porter_oval).toBitmap()!!
    private val rectBitmap
        get() = resources.getDrawable(R.drawable.shape_porter_rect).toBitmap()!!

    private lateinit var dstBitmap: Bitmap
    private lateinit var srcBitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_porter_duff)

        dstBitmap = ovalBitmap
        srcBitmap = rectBitmap

        showAllMode()
        btn_change.setOnClickListener {
            swap()
        }
    }


    private fun swap() {
        val temp = dstBitmap
        dstBitmap = srcBitmap
        srcBitmap = temp
        showAllMode()
    }

    private fun showAllMode() {
        iv_dst.setImageBitmap(dstBitmap)
        iv_src.setImageBitmap(srcBitmap)

        for (referencedId in flow_porter.referencedIds) {
            root_porter.removeView(findViewById<View>(referencedId))
        }

        val maxWidth = dstBitmap.width.coerceAtLeast(srcBitmap.width)
        val idList = mutableListOf<Int>()
        for (element in PorterDuff.Mode.values()) {
            val finalBitmap = Bitmap.createBitmap(maxWidth, maxWidth, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(finalBitmap)
            val paint = Paint()
            canvas.drawBitmap(dstBitmap, 0F, 0F, paint)
            paint.xfermode = PorterDuffXfermode(element)
            canvas.drawBitmap(srcBitmap, 0F, 0F, paint)

            val lv = LinearLayout(this)
            lv.orientation = LinearLayout.VERTICAL
            lv.id = View.generateViewId()

            val iv = ImageView(this)
            iv.setImageBitmap(finalBitmap)
            lv.addView(iv)

            val tv = TextView(this)
            tv.text = element.name
            tv.gravity = Gravity.CENTER
            lv.addView(tv)
            tv.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT

            root_porter.addView(lv)
            idList.add(lv.id)
        }
        flow_porter.referencedIds = idList.toIntArray()
        root_porter.requestLayout()
    }
}