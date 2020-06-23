package com.gdeer.gdtesthub.view.textview.baseuse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdeer.gdtesthub.R

class BaseUseFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): BaseUseFragment {
            return BaseUseFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_text_base_use, container, false)
    }
}