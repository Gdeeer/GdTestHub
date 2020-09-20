package com.gdeer.gdtesthub.view.textview.hotfix

import android.os.Bundle
import android.view.View
import com.gdeer.gdtesthub.BaseFragment
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.fragment_hot.*

class HotFragment : BaseFragment() {
    override fun getLayout(): Int {
        return R.layout.fragment_hot
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        tvHot.text = getString(R.string.hello_hot)
    }
}