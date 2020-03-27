package com.gdeer.gdtesthub.pluginfy.androidClass

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_common.*

/**
 * 普通 Activity，点击启动未注册的 targetActivity
 */
class CommonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        btn_start_target.setOnClickListener {
            startActivity(Intent(this, TargetActivity::class.java))
        }
    }
}
