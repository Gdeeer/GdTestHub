package com.gdeer.gdtesthub.view.textview.hotfix

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import com.gdeer.gdtesthub.R

class HotTextView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        if (!HotTestMap.map.isEmpty()) {
            val set = intArrayOf(
                    android.R.attr.text // index 0
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
                text = replaceText
            }
        }

        // 虽然不知道为什么
        // 但
        // hint 要在 text 下面
        // hello 要在 bye 下面（其他顺序没有限制，否则 ta.indexCount 的值会少一个）
        // 它们的值：hello > bye >> hint > text
        val set4 = intArrayOf(
                android.R.attr.text,
                android.R.attr.hint,
                R.styleable.HotTextView[R.styleable.HotTextView_bye],
                R.styleable.HotTextView[R.styleable.HotTextView_hello]
        )

        Log.d("zhangjl", "R.styleable.HotTextView: " + R.styleable.HotTextView.asList())
        Log.d("zhangjl", "R.styleable.HotTextView_hello: " + R.styleable.HotTextView_hello)
        Log.d("zhangjl", "R.styleable.HotTextView_bye: " + R.styleable.HotTextView_bye)
        Log.d("zhangjl", "android.R.attr.text: " + android.R.attr.text)
        Log.d("zhangjl", "R.styleable.Toolbar_title: " + R.styleable.Toolbar_title)
        Log.d("zhangjl", "R.styleable.Toolbar[R.styleable.Toolbar_title]: " + R.styleable.Toolbar[R.styleable.Toolbar_title])

        val ta = context.obtainStyledAttributes(attrs, set4)
        Log.d("zhangjl", "ta.indexCount: ${ta.indexCount}")
        val textId = ta.getResourceId(0, 0)
        val textId1 = ta.getResourceId(1, 0)
        val textId2 = ta.getResourceId(2, 0)
        val textId3 = ta.getResourceId(3, 0)
        Log.d("zhangjl", "text0: ${resources.getText(textId)}")
        Log.d("zhangjl", "text1: ${resources.getText(textId1)}")
        Log.d("zhangjl", "text2: ${resources.getText(textId2)}")
        Log.d("zhangjl", "text3: ${resources.getText(textId3)}")
        ta.recycle()
    }
}