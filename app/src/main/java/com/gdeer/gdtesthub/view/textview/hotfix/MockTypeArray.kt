package com.gdeer.gdtesthub.view.textview.hotfix

import android.content.res.Resources
import android.content.res.TypedArray
import android.util.Log
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class MockTypeArray(val resources: Resources, val mBase: Any?) : InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        if ("getText" == method!!.name) {
            val a = args?.get(0)
            if (!HotTestMap.map.isEmpty() && a is Int && mBase is TypedArray) {
                val textId = mBase.getResourceId(a, 0)
                val textIdName = try {
                    resources.getResourceEntryName(textId)
                } catch (e: Exception) {
                    ""
                }
                val keySet = HotTestMap.map.keys
                if (keySet.contains(textIdName)) {
                    Log.d("zhangjl", "MockTypeArray replace: id = $textId idName: $textIdName")
                    return HotTestMap.map[textIdName] ?: ""
                }
            }
        }
        return method.invoke(mBase, args)
    }
}