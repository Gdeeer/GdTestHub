package com.gdeer.gdtesthub.pluginfy.androidClass

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gdeer.gdtesthub.R

/**
 * 桩 Activity，已在 androidManifest 中注册
 */
class StubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stub)
    }
}
