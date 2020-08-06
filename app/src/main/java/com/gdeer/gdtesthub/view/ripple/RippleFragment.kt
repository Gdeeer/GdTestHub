package com.gdeer.gdtesthub.view.ripple

import android.os.Bundle
import android.view.View
import com.gdeer.gdtesthub.BaseFragment
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.fragment_ripple.*

class RippleFragment : BaseFragment(), View.OnClickListener {
    companion object {
        fun newInstance(): RippleFragment {
            val args = Bundle()

            val fragment = RippleFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_ripple
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flRipple.setOnClickListener(this)
        tvRippleBg.setOnClickListener(this)
        tvRippleFg.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // TODO: 2020/7/30
    }
}