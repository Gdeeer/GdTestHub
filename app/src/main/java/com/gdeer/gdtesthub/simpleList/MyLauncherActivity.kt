package com.gdeer.gdtesthub.simpleList

import android.app.LauncherActivity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.gdeer.gdtesthub.anr.AnrActivity
import com.gdeer.gdtesthub.finish.FinishActivity
import com.gdeer.gdtesthub.launchTime.LaunchTimeActivity

class MyLauncherActivity : LauncherActivity() {
    private val name = arrayOf(
            "launchTimeTest",
            "finishTest",
            "ANRTest"
    )
    private val clazzs = arrayOf(
            LaunchTimeActivity::class.java,
            FinishActivity::class.java,
            AnrActivity::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, name)
        listAdapter = adapter
    }

    override fun intentForPosition(position: Int): Intent {
        return Intent(this, clazzs[position])
    }
}
