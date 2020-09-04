package com.gdeer.gdtesthub.fragment

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.fragment.ui.fragmentFirst.FirstFragment

class FragmentLifeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // 禁止 fragment 恢复
        // savedInstanceState?.remove("android:support:fragments")

        Log.d("zhangjl", "FragmentLifeActivity onCreate beforeSuper $savedInstanceState ${supportFragmentManager.fragments.size} " + this.hashCode())
        super.onCreate(savedInstanceState)
        Log.d("zhangjl", "FragmentLifeActivity onCreate afterSuper $savedInstanceState ${supportFragmentManager.fragments.size}")
        setContentView(R.layout.fragment_life_activity)

        // 在 onCreate 中 add 一个 Fragment，再杀死 Activity，再恢复，会有两个 Fragment
        // 一个是在 super.onCreate 中恢复的 Fragment，一个是在后面又 add 的 Fragment
        // replace 也一样，会有两个，不过后面的会覆盖掉前面的

//        frag_container.setOnClickListener {
        supportFragmentManager.beginTransaction()
                .addToBackStack("a")
                .add(R.id.frag_container, FirstFragment.newInstance())
                .commit()
//        }

        Log.d("zhangjl", "fragments afterCommit fragments size: ${supportFragmentManager.fragments.size}")
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
