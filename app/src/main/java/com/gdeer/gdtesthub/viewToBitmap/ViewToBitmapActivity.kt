package com.gdeer.gdtesthub.viewToBitmap

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_view_to_bitmap.*

class ViewToBitmapActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_to_bitmap)

        textview.text = "hello world"
        textview.setSingleLine()
        textview.gravity = Gravity.RIGHT
        textview.setBackgroundColor(Color.GREEN)

        button.setOnClickListener {
            val bitmap = textview.toBitmap(textview.width, textview.height)
            imageView.setImageBitmap(bitmap)
        }

        button2.setOnClickListener {
            val tvTemp = TextView(this)
            tvTemp.setSingleLine()
            tvTemp.gravity = Gravity.RIGHT
            tvTemp.text = "yiyiyo"
            tvTemp.setBackgroundColor(Color.GREEN)
            tvTemp.invalidate()

            val bitmap = tvTemp.toBitmap(textview.width, textview.height)
            imageView2.setImageBitmap(bitmap)
        }
    }

    private fun viewToBitmap(view: View, viewWidth: Int, viewHeight: Int): Bitmap {
        view.layout(0, 0, viewWidth, viewHeight)
        view.buildDrawingCache()
        return view.drawingCache
    }


}
