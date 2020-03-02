package com.gdeer.gdtesthub.webview

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_webview.*

/**
 * WebView 的简单测试
 */
class WebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)


        webview.loadUrl("https://www.baidu.com/")

        webview.setOnScrollChangeListener { l, t, oldl, oldt ->

        }

        btn_check_scroll.setOnClickListener {
            Log.d("zhangjl", "!webview.canScrollVertically(-1): " + !webview.canScrollVertically(-1))
        }
    }
}
