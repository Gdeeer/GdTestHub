package com.gdeer.gdtesthub.logcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gdeer.gdtesthub.R
import com.google.i18n.phonenumbers.PhoneNumberUtil

class LogcatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logcat)

        val s = "11839203333"
        // 见 CountryCodeToRegionCodeMap
        val phoneNumber = PhoneNumberUtil.getInstance().parse(s, "CN")
        Log.d("zhangjl", "isvalidate: " + PhoneNumberUtil.getInstance().isValidNumber(phoneNumber))
    }
}
