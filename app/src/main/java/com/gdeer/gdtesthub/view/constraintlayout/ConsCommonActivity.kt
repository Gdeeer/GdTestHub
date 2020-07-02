package com.gdeer.gdtesthub.view.constraintlayout

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R

class ConsCommonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cons_common)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.root_cons_common, FlowFragment.newInstance())
                    .commitNow()
        }
    }
}
