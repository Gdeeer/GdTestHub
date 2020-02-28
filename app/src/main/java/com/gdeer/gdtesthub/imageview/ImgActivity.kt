package com.gdeer.gdtesthub.imageview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_img.*

class ImgActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_img)

        iv_pressed.setOnClickListener {
            Log.d("zhangjl", "onClick")
        }
    }
}
