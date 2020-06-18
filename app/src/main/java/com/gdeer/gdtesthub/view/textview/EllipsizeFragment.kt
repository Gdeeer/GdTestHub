package com.gdeer.gdtesthub.view.textview

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.fragment_ellipsize.*

/**
 * Ellipsize 测试的 Fragment
 */
class EllipsizeFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): EllipsizeFragment {
            return EllipsizeFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ellipsize, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 使用 maxlines + ellipsize 实现 ...
        // 添加 TextWatcher 后 setText，需要加上 singleLine 属性，... 才能出现
        tvElli.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d("zhangjl", "afterTextChanged")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("zhangjl", "beforeTextChanged")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("zhangjl", "onTextChanged")
            }
        })

        tvElli.setOnClickListener {
            tvElli.text = "tvEllitexttvEllitexttvtvEllitexttvEllitexttvtvEllitexttvEllitexttvtvEllitexttvEllitexttv"
        }
    }
}