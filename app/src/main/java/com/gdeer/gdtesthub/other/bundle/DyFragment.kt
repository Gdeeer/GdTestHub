package com.gdeer.gdtesthub.other.bundle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.alibaba.android.arouter.utils.Consts
import com.gdeer.gdtesthub.BaseFragment
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.fragment_dy.*
import java.util.*

/**
 * 启动 DyMainActivity 的 Fragment
 */
class DyFragment : BaseFragment() {
    override fun getLayout(): Int {
        return R.layout.fragment_dy
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnStartDy.setOnClickListener {
            ARouter.getInstance().build("/dynamic/main")
                    .navigation()
        }

        val routerMap = HashSet(context!!.getSharedPreferences(Consts.AROUTER_SP_CACHE_KEY, Context.MODE_PRIVATE).getStringSet(Consts.AROUTER_SP_KEY_MAP, HashSet()))
        Log.d("zhangjl", "routerMap: $routerMap")
    }
}