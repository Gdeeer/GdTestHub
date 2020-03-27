package com.gdeer.gdtesthub.viewpager.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gdeer.gdtesthub.R

/**
 * A placeholder fragment containing a simple view.
 */
class OuterFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    private var mTag = TAG

    private var mView: View? = null

    private var mInnerFragment: InnerFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(mTag, "onCreate")
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(mTag, "onCreateView mView ${if (mView == null) "==" else "!="} null"
                + " outer: $this"
                + " inner: $mInnerFragment"
                + " frags: ${childFragmentManager.findFragmentById(R.id.view_bottom)}")

        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_tab, container, false)
            val textView: TextView? = mView?.findViewById(R.id.section_label)
            pageViewModel.text.observe(this, Observer<String> {
                textView?.text = it
            })
            val transaction = childFragmentManager.beginTransaction()
            mInnerFragment = InnerFragment.newInstance()
            Log.d(mTag, "InnerFragment.newInstance() $mInnerFragment")
            transaction.replace(R.id.view_bottom, mInnerFragment!!)
            transaction.commit()
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
        private const val TAG = "OuterFragment"
        private const val ARG_SECTION_NUMBER = "section_number"
        @JvmStatic
        fun newInstance(sectionNumber: Int): OuterFragment {
            return OuterFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
                mTag = "${TAG}_$sectionNumber"
            }
        }
    }
}