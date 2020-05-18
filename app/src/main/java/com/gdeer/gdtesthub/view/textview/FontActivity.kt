package com.gdeer.gdtesthub.view.textview

import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.slice.SliceManager
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_font.*

class FontActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font)

        val manager = SliceManager.getInstance(this)
        val mainUri = Uri.parse("content://com.gdeer.gdtesthub/font")
        manager.grantSlicePermission("com.google.android.googlequicksearchbox", mainUri)
        manager.grantSlicePermission("com.google.android.gms", mainUri)

        val s = "hello我是123"
        tv_font_1.text = s

        // "hello我是" 仍是系统字体；"123" 是设置的 numbers 字体
        val typeface = Typeface.createFromAsset(assets, "fonts/hel.ttf")
        tv_font_2.typeface = typeface
        tv_font_21.typeface = typeface
        tv_font_22.typeface = typeface

        val typefaceDin = Typeface.createFromAsset(assets, "fonts/din.ttf")
        tv_font_31.typeface = typefaceDin
        tv_font_32.typeface = typefaceDin
        tv_font_33.typeface = typefaceDin
    }
}
