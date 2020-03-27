package com.gdeer.gdtesthub.jump

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_target.*

class TargetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("zhangjl", "target onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)

        webview.loadUrl("https://blog.csdn.net/gdeer")
    }

    override fun onStart() {
        Log.d("zhangjl", "target onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("zhangjl", "target onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("zhangjl", "target onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("zhangjl", "target onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("zhangjl", "target onDestroy")
        super.onDestroy()
    }
}
