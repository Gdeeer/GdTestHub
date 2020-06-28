package com.gdeer.gdtesthub.fragment

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.fragment.ui.fragmentFirst.FirstFragment
import kotlinx.android.synthetic.main.fragment_life_activity.*

class FragmentLifeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("zhangjl", "FragmentLifeActivity onCreate beforeSuper $savedInstanceState ${supportFragmentManager.fragments.size}")
        super.onCreate(savedInstanceState)
        Log.d("zhangjl", "FragmentLifeActivity onCreate afterSuper $savedInstanceState ${supportFragmentManager.fragments.size}")
        setContentView(R.layout.fragment_life_activity)

        frag_container.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.frag_container, FirstFragment.newInstance())
                    .commitNow()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d("zhangjl", "FragmentLifeActivity onRestoreInstanceState $savedInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        Log.d("zhangjl", "FragmentLifeActivity onSaveInstanceState")
        super.onSaveInstanceState(outState, outPersistentState)
        outState.remove("android:support:fragments")
    }

    override fun onDestroy() {
        Log.d("zhangjl", "FragmentLifeActivity onDestroy")
        super.onDestroy()
    }
}
