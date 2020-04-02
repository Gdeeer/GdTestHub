package com.gdeer.gdtesthub.leak

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.activity_leak.*
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference

class LeakActivity : AppCompatActivity() {

    private lateinit var softRef: SoftReference<MutableList<Byte>>
    private lateinit var weakRef: WeakReference<MutableList<Byte>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leak)

        val cacheDataSoft = MutableList(10 * 1024 * 1024) { it.toByte() }
        softRef = SoftReference(cacheDataSoft)

        val cacheDataWeak = MutableList(53 * 1024 * 1024) { it.toByte() }
        weakRef = WeakReference(cacheDataWeak)

        btn_soft_gc.setOnClickListener {
            System.gc()
            printSoftRef()
            if (softRef.get() != null) {
                // 对软引用来说，一直创建临时变量也不行，看来得用 java 命令行指定内存大小才能模拟了
                MutableList(20 * 1000 * 1024) { it.toByte() }
            }
        }

        btn_wake_gc.setOnClickListener {
            // System.gc() 不一定触发 gc
            System.gc()
            printWeakRef()
            if (weakRef.get() != null) {
                // 创建临时变量，触发 gc，导致 weakRef 引用对象被回收
                MutableList(10 * 1000 * 1024) { it.toByte() }
            }
        }
    }

    fun printSoftRef() {
        Log.d(TAG, "softRef.get ${if (softRef.get() == null) "==" else "!="} null")
    }

    fun printWeakRef() {
        Log.d(TAG, "weakRef.get ${if (weakRef.get() == null) "==" else "!="} null")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()

        sWeakReference = WeakReference(this)
        printWeakReference()
        // 如果给 sActivity 赋值，那延迟之后，activity 不会被回收
        // sActivity = this
        System.gc()
        // 如果只剩 sWeakReference 引用 Activity, 延迟之后，activity 会被回收
        printDelay()
    }

    companion object {
        val TAG: String = "TAG_" + LeakActivity::class.java.simpleName
        lateinit var sActivity: Activity
        lateinit var sWeakReference: WeakReference<Activity>
        val handler = Handler()

        fun printWeakReference() {
            Log.d(TAG, "sWeakRef.get ${if (sWeakReference.get() == null) "==" else "!="} null")
        }

        fun printDelay() {
            handler.postDelayed({ printWeakReference() }, 6000)
        }
    }
}
