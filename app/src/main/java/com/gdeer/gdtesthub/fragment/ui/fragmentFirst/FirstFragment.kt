package com.gdeer.gdtesthub.fragment.ui.fragmentFirst

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.fragment.ui.fragmentSecond.SecondFragment
import kotlinx.android.synthetic.main.fragment_life_fragment.*

class FirstFragment : Fragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    private lateinit var mViewModel: FirstViewModel

    override fun onAttach(context: Context?) {
        Log.d("zhangjl", "first onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("zhangjl", "first onCreate")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.d("zhangjl", "first onCreateView")
        return inflater.inflate(R.layout.fragment_life_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("zhangjl", "first onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)
        // TODO: Use the ViewModel

        btn_start.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, SecondFragment.newInstance())
                    ?.commitNow()
        }
    }

    override fun onStart() {
        Log.d("zhangjl", "first onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("zhangjl", "first onResume")
        super.onResume()
    }

    override fun onPause() {
        Log.d("zhangjl", "first onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("zhangjl", "first onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("zhangjl", "first onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d("zhangjl", "first onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("zhangjl", "first onDetach")
        super.onDetach()
    }
}
