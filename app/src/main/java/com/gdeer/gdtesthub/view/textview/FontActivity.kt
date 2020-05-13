package com.gdeer.gdtesthub.view.textview

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_font.*

class FontActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font)


        val s = "hello我是123"
        tv_font_1.text = s
        tv_font_2.text = s

        // "hello我是" 仍是系统字体；"123" 是设置的 numbers 字体
        val typeface = Typeface.createFromAsset(assets, "fonts/numbers.ttf")
        tv_font_2.typeface = typeface
    }
}
