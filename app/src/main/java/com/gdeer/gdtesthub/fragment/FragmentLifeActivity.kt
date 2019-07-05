package com.gdeer.gdtesthub.fragment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.fragment.ui.fragmentFirst.FirstFragment

class FragmentLifeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_life_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, FirstFragment.newInstance())
                    .commitNow()
        }
    }

}
