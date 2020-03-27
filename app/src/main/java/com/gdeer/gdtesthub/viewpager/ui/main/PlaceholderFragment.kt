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
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    private var mTag = TAG

    private var mView: View? = null

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
        Log.d(mTag, "onCreateView mView == null: ${mView == null}")
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_tab, container, false)
            val textView: TextView? = mView?.findViewById(R.id.section_label)
            pageViewModel.text.observe(this, Observer<String> {
                textView?.text = it
            })
        }
        return mView
    }

    override fun onResume() {
        Log.d(mTag, "onResume")
        super.onResume()
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
        private const val TAG = "PlaceholderFragment"
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
                mTag = "${TAG}_$sectionNumber"
            }
        }
    }
}