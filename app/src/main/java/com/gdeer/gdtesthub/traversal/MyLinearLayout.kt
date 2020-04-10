package com.gdeer.gdtesthub.traversal

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout

class MyLinearLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {
    override fun requestLayout() {
        Log.d(RequestLayoutActivity.TAG, "MyLinearLayout requestLayout")
        super.requestLayout()
    }

    override fun invalidate() {
        Log.d(RequestLayoutActivity.TAG, "MyLinearLayout invalidate")
        super.invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d(RequestLayoutActivity.TAG, "MyLinearLayout onMeasure")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.d(RequestLayoutActivity.TAG, "MyLinearLayout onLayout")
        super.onLayout(changed, l, t, r, b)
    }

    override fun dispatchDraw(canvas: Canvas?) {
        Log.d(RequestLayoutActivity.TAG, "MyLinearLayout dispatchDraw")
        super.dispatchDraw(canvas)
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d(RequestLayoutActivity.TAG, "MyLinearLayout onDraw")
        super.onDraw(canvas)
    }
}