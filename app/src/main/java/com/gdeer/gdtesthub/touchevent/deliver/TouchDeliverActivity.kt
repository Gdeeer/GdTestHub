package com.gdeer.gdtesthub.touchevent.deliver

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.gdeer.gdtesthub.R

class TouchDeliverActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch_deliver)
    }

    /**
     * Looper
     * InputEventReceiver
     * ViewRootImpl
     * DecorView.dispatchTouchEvent
     * Activity
     * DecorView.superDispatchTouchEvent
     * ViewGroup
     */
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(TAG, "dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    companion object {
        val TAG = "TAG_TouchDeliver"
    }
}
