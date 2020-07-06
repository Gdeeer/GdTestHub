package com.gdeer.gdtesthub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gdeer.gdtesthub.restart.RestartFragment

class CommonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        if (savedInstanceState == null) {
            val fragment = RestartFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.root_common, fragment)
                    .commitNow()
        }
    }
}