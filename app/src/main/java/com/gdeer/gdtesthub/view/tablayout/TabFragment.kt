package com.gdeer.gdtesthub.view.tablayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.fragment_tab_layout.*

class TabFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): TabFragment {
            return TabFragment()
        }
    }

    private lateinit var adapter: ChildPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChildPagerAdapter(childFragmentManager)
        pager.adapter = adapter
        tab_layout.setupWithViewPager(pager)

        for (i in 0..tab_layout.tabCount) {
            val tab = tab_layout.getTabAt(i);//获得每一个tab
            tab?.setCustomView(R.layout.layout_item_tab);//给每一个tab设置view
            var s = "test"
            for (j in 0..i) {
                s += i.toString()
            }
            tab?.customView?.findViewById<TextView>(R.id.tv_test)?.text = s
        }
    }
}

class ChildPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        val fragment = ChildFragment.newInstance()
        fragment.arguments = Bundle().apply {
            // Our object is just an integer :-P
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }

    override fun getCount(): Int = 10

    override fun getPageTitle(position: Int): CharSequence {
        return "OBJECT ${(position + 1)}"
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}