package com.gdeer.gdtesthub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.view.ripple.RippleFragment

class CommonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        if (savedInstanceState == null) {
            val fragment = RippleFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.root_common, fragment)
                    .commitNow()
        }
    }
}