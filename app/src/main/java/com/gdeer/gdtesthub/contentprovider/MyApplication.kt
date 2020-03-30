package com.gdeer.gdtesthub.contentprovider

import android.app.Application
import android.content.Context
import android.util.Log

/**
 * ContentProvider 与 Application 的生命周期：
 * > application attachBaseContext
 * > contentProvider onCreate
 * > application onCreate
 */
class MyApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        Log.d(MyContentProvider.TAG, "application attachBaseContext")
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        Log.d(MyContentProvider.TAG, "application onCreate")
        super.onCreate()
    }
}