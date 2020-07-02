package com.gdeer.gdtesthub.view.constraintlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdeer.gdtesthub.R

class MotionLayoutFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): MotionLayoutFragment {
            return MotionLayoutFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.activity_motion_layout_img_filter_view, container, false)
        return inflater.inflate(R.layout.activity_cons_common, container, false)
    }
}