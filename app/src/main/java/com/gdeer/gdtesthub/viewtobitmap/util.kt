package com.gdeer.gdtesthub.viewtobitmap

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View

fun View.toBitmap(viewWidth: Int, viewHeight: Int): Bitmap? {
    layout(0, 0, viewWidth, viewHeight)
    buildDrawingCache()
    return drawingCache
}

fun View.toBitmap2(): Bitmap? {
    var bitmap: Bitmap? = null
    // 创建对应大小的bitmap
    try {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap!!)
        draw(canvas)
    } catch (t: Throwable) {
        t.printStackTrace()
    }

    return bitmap
}