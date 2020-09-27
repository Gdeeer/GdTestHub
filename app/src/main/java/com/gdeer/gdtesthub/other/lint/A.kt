package com.gdeer.gdtesthub.other.lint

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.gdeer.gdtesthub.BaseFragment
import com.gdeer.gdtesthub.R

class LintFragment : BaseFragment() {

    override fun getLayout(): Int {
        return R.layout.fragment_lint
    }

    fun foo(context: Context) {
        Log.d("zhangjl", "111")
        val a = TextView(context)
        val b = EditText(context)
        val c = Button(context)
    }
}

class B @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

}

open class C @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : Button(context, attrs, defStyleAttr) {

}

class D @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : C(context, attrs, defStyleAttr) {

}