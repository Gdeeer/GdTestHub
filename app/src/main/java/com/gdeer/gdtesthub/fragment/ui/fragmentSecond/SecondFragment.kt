package com.gdeer.gdtesthub.fragment.ui.fragmentSecond

import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gdeer.gdtesthub.R

class SecondFragment : androidx.fragment.app.Fragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    private lateinit var viewModel: SecondViewModel

    override fun onAttach(context: Context) {
        Log.d("zhangjl", "second onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("zhangjl", "second onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("zhangjl", "second onCreateView")
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SecondViewModel::class.java)
        // TODO: Use the ViewModel
    }


    override fun onStart() {
        Log.d("zhangjl", "second onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("zhangjl", "second onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("zhangjl", "second onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("zhangjl", "second onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("zhangjl", "second onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("zhangjl", "second onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("zhangjl", "second onDetach")
        super.onDetach()
    }
}
