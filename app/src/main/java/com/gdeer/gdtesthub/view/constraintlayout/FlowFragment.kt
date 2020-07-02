package com.gdeer.gdtesthub.view.constraintlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdeer.gdtesthub.R

/**
 * Flow 不支持 item 设置 margin
 */
class FlowFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): FlowFragment {
            return FlowFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_flow, container, false)
    }
}