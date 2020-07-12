package com.gdeer.gdtesthub.lib.fastjson

import androidx.annotation.Keep
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.annotation.JSONField

object FastJsonObject {
    fun execParse(): A {
        val s = "{\"name\":\"gdeer\"}"
//        val s = JSON.toJSONString(a)
        return JSON.parseObject(s, A::class.java)
    }
}

@Keep
data class A(
        val name: String?,
        val age: Int
)