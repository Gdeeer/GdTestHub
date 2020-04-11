package com.gdeer.gdtesthub.view.viewpager.ui.main

import android.content.Context
import androidx.fragment.app.FragmentPagerAdapter

private val TAB_TITLES = arrayOf(
        "tab1", "tab2", "tab3", "tab4", "tab5"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: androidx.fragment.app.FragmentManager)
    : androidx.fragment.app.FragmentStatePagerAdapter(fm) {

    val itemFrags = arrayListOf<OuterFragment>()

    override fun getItem(position: Int): androidx.fragment.app.Fragment {
        return itemFrags[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return itemFrags.size
    }

    override fun getItemPosition(`object`: Any): Int {
        return androidx.viewpager.widget.PagerAdapter.POSITION_NONE
    }
}