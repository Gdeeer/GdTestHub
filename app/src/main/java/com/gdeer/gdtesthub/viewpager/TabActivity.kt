package com.gdeer.gdtesthub.viewpager

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.viewpager.ui.main.OuterFragment
import com.gdeer.gdtesthub.viewpager.ui.main.SectionsPagerAdapter

class TabActivity : AppCompatActivity() {

    private val itemFrags = arrayListOf<OuterFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab)

        for (i in 1..1) {
            val frag = OuterFragment.newInstance(i)
            itemFrags.add(frag)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        sectionsPagerAdapter.itemFrags.addAll(itemFrags)

        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener {
            sectionsPagerAdapter.notifyDataSetChanged()
        }
    }
}