package com.gdeer.gdtesthub.view.textview.hotfix

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatEditText

class HotEditText @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatEditText(context, attrs, defStyleAttr) {

    init {
        if (!HotTestMap.map.isEmpty()) {
            val set = intArrayOf(
                    android.R.attr.hint // index 0
            )
            val ta = context.obtainStyledAttributes(attrs, set)
            val textId = ta.getResourceId(0, Resources.ID_NULL)
            val textIdName = try {
                resources.getResourceEntryName(textId)
            } catch (e: Exception) {
                ""
            }
            ta.recycle()

            val keySet = HotTestMap.map.keys
            if (keySet.contains(textIdName)) {
                Log.d("zhangjl", "HotTextView replace: id = $id idName: $textIdName")
                val replaceText = HotTestMap.map[textIdName] ?: ""
                setHint(replaceText)
            }
        }
    }
}