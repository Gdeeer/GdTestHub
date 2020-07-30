package com.gdeer.gdtesthub.view.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.fragment_child_tab.*

const val ARG_OBJECT = "object"

class ChildFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): ChildFragment {
            return ChildFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_child_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            tv_content.text = getInt(ARG_OBJECT).toString()
        }
    }
}








