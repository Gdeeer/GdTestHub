package com.gdeer.gdtesthub;

import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.gdeer.gdtesthub.view.textview.hotfix.HotFragment
import com.gdeer.gdtesthub.view.textview.hotfix.MockTypeArray
import com.gdeer.gdtesthub.view.textview.hotfix.MyResources
import java.lang.reflect.Proxy

@Route(path = "/gdeer/common")
class CommonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)

        if (savedInstanceState == null) {
            val fragment = HotFragment()
            supportFragmentManager.beginTransaction()
                    .replace(R.id.root_common, fragment)
                    .commitNow()
        }
    }

    //    override fun onWindowFocusChanged(hasFocus: Boolean) {
//        super.onWindowFocusChanged(hasFocus)
//        Log.d("zhangjl", "onWindowFocusChanged: " + System.currentTimeMillis())
//    }
//
    override fun getResources(): Resources {
        val superRe = super.getResources()
        return MyResources(superRe.assets, superRe.displayMetrics, superRe.configuration)
    }

//    override fun getTheme(): Resources.Theme {
//        return Resources.Theme()
//    }

    override fun getTheme(): Resources.Theme {
        val superTheme = super.getTheme()
        return (Proxy.newProxyInstance(
                Thread.currentThread().contextClassLoader,
                arrayOf<Class<*>>(Resources.Theme::class.java),
                MockTypeArray(resources, superTheme)) as Resources.Theme)
    }
}