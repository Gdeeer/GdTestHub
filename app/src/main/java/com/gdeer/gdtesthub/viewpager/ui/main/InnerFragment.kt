package com.gdeer.gdtesthub.viewpager.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdeer.gdtesthub.R

/**
 * A placeholder fragment containing a simple view.
 */
class InnerFragment : androidx.fragment.app.Fragment() {

    private var mTag = TAG + "_" + this

    private var mView: View? = null

    init {
        Log.d(mTag, "constructor: $this")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(mTag, "onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(mTag, "onCreateView mView ${if (mView == null) "==" else "!="} null")
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_tab_inner, container, false)
        }
        return mView
    }

    override fun onResume() {
        Log.d(mTag, "onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d(mTag, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(mTag, "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(mTag, "onResume")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(mTag, "onDestroy")
        super.onDestroy()
    }

    companion object {
        private const val TAG = "InnerFragment"
        @JvmStatic
        fun newInstance(): InnerFragment {
            return InnerFragment()
        }
    }
}