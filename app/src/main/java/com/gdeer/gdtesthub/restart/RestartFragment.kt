package com.gdeer.gdtesthub.restart

import android.content.Intent
import android.os.Bundle
import android.os.Process
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.fragment_restart.*
import java.util.*
import kotlin.system.exitProcess

class RestartFragment constructor(contentLayoutId: Int) : Fragment(contentLayoutId) {
    companion object {

        fun newInstance(): RestartFragment {
            val args = Bundle()

            val fragment = RestartFragment(R.layout.fragment_restart)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_restart.setOnClickListener {
            // 重启 app
            activity?.let {
                val intent = activity!!.packageManager
                        .getLaunchIntentForPackage(activity!!.packageName)
                intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                Process.killProcess(Process.myPid())
                // kotlin 的 System.exit(0)
                exitProcess(0)
            }
        }
    }
}