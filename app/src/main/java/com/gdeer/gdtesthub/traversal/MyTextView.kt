package com.gdeer.gdtesthub.traversal

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView

class MyTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        TextView(context, attrs, defStyleAttr) {
    override fun requestLayout() {
        Log.d(RequestLayoutActivity.TAG, "MyTextView requestLayout")
        super.requestLayout()
    }

    override fun invalidate() {
        Log.d(RequestLayoutActivity.TAG, "MyTextView invalidate")
        super.invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d(RequestLayoutActivity.TAG, "MyTextView onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.d(RequestLayoutActivity.TAG, "MyTextView onLayout")
        super.onLayout(changed, l, t, r, b)
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d(RequestLayoutActivity.TAG, "MyTextView onDraw")
        super.onDraw(canvas)
    }
}