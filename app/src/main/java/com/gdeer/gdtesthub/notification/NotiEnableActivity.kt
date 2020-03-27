package com.gdeer.gdtesthub.notification

import android.content.Context
import android.os.Bundle
import androidx.core.app.NotificationManagerCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.gdeer.gdtesthub.R

class NotiEnableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noti_enable)

        val bo = isNotificationEnabled(this)
        Log.d("zhangjl", "isAccessibilityEnabled: $bo")
    }


    fun isNotificationEnabled(context: Context): Boolean {
        var isOpened = false
        try {
            isOpened = NotificationManagerCompat.from(context).areNotificationsEnabled()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return isOpened

    }
}
