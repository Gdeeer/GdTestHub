package com.gdeer.gdtesthub.startflag

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_b.*

class ActivityB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)

        Log.d("zhangjl", "B onCreate intent: $intent")

        btn_b_start.setOnClickListener {

            var cl: Class<out Activity>? = null
            if (rb_1.isChecked) {
                cl = ActivityA::class.java
            }
            if (rb_2.isChecked) {
                cl = ActivityB::class.java
            }
            if (rb_3.isChecked) {
                cl = ActivityC::class.java
            }
            val intent = Intent(this, cl)

            if (cb_new_task.isChecked) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            if (cb_single_top.isChecked) {
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            }

            if (cb_clear_top.isChecked) {
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            }
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        Log.d("zhangjl", "B onNewIntent: $intent")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        Log.d("zhangjl", "B onActivityResult: $requestCode $resultCode $data")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("zhangjl", "B onDestroy")
    }
}
