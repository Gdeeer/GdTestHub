package com.gdeer.gdtesthub.view.textview

import android.os.Bundle
import android.view.View
import com.gdeer.gdtesthub.BaseFragment
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.fragment_marquee.*

class MarqueeFragment: BaseFragment() {
    companion object {
        fun newInstance(): MarqueeFragment {
            val args = Bundle()

            val fragment = MarqueeFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun getLayout(): Int {
        return R.layout.fragment_marquee
    }
}