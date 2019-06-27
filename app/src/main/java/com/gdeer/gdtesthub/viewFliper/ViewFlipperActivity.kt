package com.gdeer.gdtesthub.viewFliper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ViewFlipper
import com.gdeer.gdtesthub.R

class ViewFlipperActivity : AppCompatActivity() {

    lateinit var vf: ViewFlipper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_flipper)

        vf = findViewById(R.id.vf)

        vf.startFlipping()
    }
}
