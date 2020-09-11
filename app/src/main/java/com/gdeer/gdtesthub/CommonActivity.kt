package com.gdeer.gdtesthub

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.gdeer.gdtesthub.other.bundle.DyFragment

@Route(path = "/gdeer/common")
class CommonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        if (savedInstanceState == null) {
            val fragment = DyFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.root_common, fragment)
                    .commitNow()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d("zhangjl", "onWindowFocusChanged: " + System.currentTimeMillis())
    }
}