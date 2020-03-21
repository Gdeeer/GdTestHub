package com.gdeer.gdtesthub.touchevent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MotionEvent
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_touch_event.*

class TouchEventActivity : AppCompatActivity() {

    val MAX_MOVE_COUNT = 5
    var moveCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_event)

        btn_touch_event.setOnTouchListener { v, event ->
            Log.d("zhangjl", getAction(event.action))
            false
        }
    }

    fun getAction(actionCode: Int): String {
        return when (actionCode) {
            0 -> "down"
            1 -> "up"
            2 -> "move"
            3 -> "cancel"
            else -> "none"
        }
    }
}
