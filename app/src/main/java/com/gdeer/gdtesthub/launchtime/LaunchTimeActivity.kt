package com.gdeer.gdtesthub.launchtime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.gdeer.gdtesthub.R

class LaunchTimeActivity : AppCompatActivity() {

    private lateinit var mTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("zhangjl", "onCreate " + System.currentTimeMillis())
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_time)

        mTv = findViewById(R.id.textView)
        mTv.setOnClickListener {
            Log.d("zhangjl", "onClick")
            reportFullyDrawn()
        }

        window.decorView.viewTreeObserver.addOnGlobalLayoutListener {
            Log.d("zhangjl", "onGlobalLayout " + System.currentTimeMillis())
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("zhangjl", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("zhangjl", "onStop")
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        Log.d("zhangjl", "onWindowFocusChanged hasFocus " + hasFocus + " " + System.currentTimeMillis())
    }
}
