package com.gdeer.gdtesthub.memory

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.device.yearclass.YearClass
import com.gdeer.gdtesthub.R

class MemoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memory)

        // 只支持到 2016
        val year = YearClass.get(this)
        Log.d("memory", "year: $year")
    }
}
