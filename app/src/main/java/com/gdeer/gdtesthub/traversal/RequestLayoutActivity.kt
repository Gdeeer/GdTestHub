package com.gdeer.gdtesthub.traversal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_request_layout.*

class RequestLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_layout)

        tv_request_layout.setOnClickListener {
            tv_request_layout.requestLayout()
        }

        tv_invalidate.setOnClickListener {
            tv_invalidate.invalidate()
        }

        root_layout_request_layout.setOnClickListener {
            root_layout_request_layout.requestLayout()
        }

        root_layout_request_layout.setOnLongClickListener {
            root_layout_request_layout.invalidate()
            true
        }
    }

    companion object {
        const val TAG = "TAG_RequestLayout"
    }
}
