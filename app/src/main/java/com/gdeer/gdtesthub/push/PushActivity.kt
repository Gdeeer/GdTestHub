package com.gdeer.gdtesthub.push

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.jpush.android.api.JPushInterface
import com.gdeer.gdtesthub.R

class PushActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_push)

        JPushInterface.setDebugMode(true)
        JPushInterface.init(this)
    }
}
