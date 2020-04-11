package com.gdeer.gdtesthub.view.imageview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
