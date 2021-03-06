package com.gdeer.gdtesthub.fragment.ui.fragmentFirst

import androidx.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
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

    override fun onAttach(context: Context) {
        Log.d("zhangjl", "first onAttach " + this.hashCode())
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("zhangjl", "first onCreate " + this.hashCode())
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Log.d("zhangjl", "first onCreateView " + this.hashCode())
        return inflater.inflate(R.layout.fragment_life_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("zhangjl", "first onActivityCreated " + this.hashCode())
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(FirstViewModel::class.java)
        // TODO: Use the ViewModel

        btn_start.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragmentlife, SecondFragment.newInstance())
                    ?.commitNow()
        }
    }

    override fun onStart() {
        Log.d("zhangjl", "first onStart " + this.hashCode())
        super.onStart()
    }

    override fun onResume() {
        Log.d("zhangjl", "first onResume " + this.hashCode())
        super.onResume()
    }

    override fun onPause() {
        Log.d("zhangjl", "first onPause " + this.hashCode())
        super.onPause()
    }

    override fun onStop() {
        Log.d("zhangjl", "first onStop " + this.hashCode())
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d("zhangjl", "first onDestroyView " + this.hashCode())
        super.onDestroyView()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("zhangjl", "first onSaveInstanceState " + this.hashCode())
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        Log.d("zhangjl", "first onDestroy " + this.hashCode())
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d("zhangjl", "first onDetach " + this.hashCode())
        super.onDetach()
    }
}
