package com.gdeer.gdtesthub.view.textview

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import kotlinx.android.synthetic.main.fragment_marquee.*

class AutoMarqueeTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    init {
        isSelected = true
    }
}