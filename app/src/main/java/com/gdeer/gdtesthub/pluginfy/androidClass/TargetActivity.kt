package com.gdeer.gdtesthub.pluginfy.androidClass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R

/**
 * 目标 Activity，未在 androidManifest 中注册
 */
class TargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stub_target)
    }
}
