package com.gdeer.gdtesthub.lib.fastjson

import android.util.Log
import androidx.annotation.Keep
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.annotation.JSONField

object FastJsonObject {
    fun execParse(): A {
//        val a = A("gd", 1, true)
//        val s = JSON.toJSONString(a)
        val s = "{\"age\":1,\"isMan\":true,\"name\":\"gd\"}"
        Log.d("zhangjl", "s: $s")
        return JSON.parseObject(s, A::class.java)
    }
}

@Keep
data class A(
        val name: String?,
        val age: Int,
        val isMan: Boolean
)