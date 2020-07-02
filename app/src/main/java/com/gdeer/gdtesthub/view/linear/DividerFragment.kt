package com.gdeer.gdtesthub.view.linear

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdeer.gdtesthub.R

/**
showDividers 必须要加

divider 可以是 drawable 或 color
如果是 drawable，则 divider 在主方向上的厚度为 drawable 在主方向的厚度，在副方向上占满 item。
如果是 color，则 divider 在主方向上厚度为 1px，在副方向上占满 item。

dividerPadding 只能增加 divider 在 linear 副方向上的 padding。
 */
class DividerFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = DividerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_divider, container, false)
    }
}