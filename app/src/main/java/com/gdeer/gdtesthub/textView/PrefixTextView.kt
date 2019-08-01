package com.gdeer.gdtesthub.textView

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.gdeer.gdtesthub.R

class PrefixTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : TextView(context, attrs, defStyleAttr) {

    private var prefixText: String?

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.PrefixTextView)
        prefixText = typedArray.getString(R.styleable.PrefixTextView_prefix)
        typedArray.recycle()
        // 再调一遍 setText，因为在 init 之前，TextView 的 super 方法里已经调过 setText 了，那时的 prefixText 还是空
        text = text
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        val prefix = prefixText ?: ""
        super.setText("$prefix$text", type)
    }
}