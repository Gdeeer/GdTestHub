package com.gdeer.gdtesthub.lambda

class KotlinLambda {
    // 完整
    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    // 简化
    val sumSimple1: (Int, Int) -> Int = { x, y -> x + y }
    val sumSimple2 = { x: Int, y: Int -> x + y }

    // 最后一个参数是函数类型，lambda 可以放到括号外
    // 三个参数
    fun a3(x: Int, y: Int, lambda: (Int, Int) -> Int): Int {
        return lambda(x, y)
    }

    val b = a3(1, 2, { x, y -> x + y })
    val bOut = a3(1, 2) { x, y -> x + y }

    // 一个参数
    fun a1(lambda: (Int, Int) -> Int): Int {
        return lambda(1, 2)
    }

    val bOut2 = a1 { x, y -> x + y }


    // lambda 只有一个参数时，可用 it 表示
    val it: (Int) -> Unit = { println(it) }

    fun itFun(lambda: (Int) -> Unit) {
        lambda(1)
    }

    val itResult = itFun { print(it) }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // lambda 的返回值
            val retLambda = {
                1 + 1
                3
            }
            val retVal = retLambda() // 值为 3

            // 无用变量的声明
            val map = mapOf(1 to "a", 2 to "b")
            map.forEach { _, value -> println(value) }

            // 闭包
            val ints = listOf(1, 2, 3)
            var sum = 0
            ints.filter { it > 0 }.forEach {
                sum += it
            }
            println(sum)

            ints.filter(fun(it): Boolean { return it > 0 })
        }
    }
}