package com.gdeer.gdtesthub.lib.fastjson

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.fragment_fastjson.*

class FastJsonFragment constructor(contentLayoutId: Int = R.layout.fragment_fastjson) : Fragment(contentLayoutId) {
    companion object {
        fun newInstance(): FastJsonFragment {
            val args = Bundle()

            val fragment = FastJsonFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val a = FastJsonObject.execParse()
        tv_fastjson.text = a.name + " " + a.age
    }
}