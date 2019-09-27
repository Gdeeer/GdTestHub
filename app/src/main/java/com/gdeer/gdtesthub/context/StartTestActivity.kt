package com.gdeer.gdtesthub.context

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.bitmap.FilterActivity
import kotlinx.android.synthetic.main.activity_start_test.*

class StartTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_test)

        // 正常启动
        // 表现正常
        btn_normal.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
        }

        // application 启动1
        // 表现正常
        // intent 里的 context 只是为了获取一个 packageName
        btn_application1.setOnClickListener {
            val intent = Intent(applicationContext, FilterActivity::class.java)
            startActivity(intent)
        }

        // application 启动2
        // targetSDKVersion 28 及以上，崩溃
        // targetSDKVersion 24-27，正常
        // targetSDKVersion 23 及以下，崩溃
        btn_application2.setOnClickListener {
            val intent = Intent(applicationContext, FilterActivity::class.java)
            applicationContext.startActivity(intent)
        }

        // 子线程启动
        // 表现正常
        btn_new_thread.setOnClickListener {
            Thread(Runnable {
                val intent = Intent(this, FilterActivity::class.java)
                startActivity(intent)
            }).start()
        }
    }
}
