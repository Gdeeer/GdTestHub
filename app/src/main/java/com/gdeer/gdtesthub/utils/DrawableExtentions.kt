package com.gdeer.gdtesthub.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable

fun String?.notEmptyApply(block: String.() -> Unit): String? {
    return if (!this.isNullOrEmpty()) {
        block()
        this
    } else {
        this
    }
}

fun Drawable.toBitmap(): Bitmap? {
    val w = intrinsicWidth
    val h = intrinsicHeight
    val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    setBounds(0, 0, w, h)
    draw(canvas)
    return bitmap
}