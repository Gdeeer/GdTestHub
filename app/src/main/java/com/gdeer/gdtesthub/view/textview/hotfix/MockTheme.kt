package com.gdeer.gdtesthub.view.textview.hotfix

import android.content.res.Resources
import android.content.res.TypedArray
import com.gdeer.gdtesthub.pluginfy.androidclass.MockAMSP
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class MockTheme(val resources: Resources, val mBase: Any?): InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        if ("obtainStyledAttributes" == method!!.name) {
            val it = method.invoke(mBase, args)
            val proxy = Proxy.newProxyInstance(
                    Thread.currentThread().contextClassLoader,
                    arrayOf<Class<*>>(TypedArray::class.java),
                    MockTypeArray(resources, mBase)
            )
            return proxy
        }
        return method.invoke(mBase, args)
    }

}