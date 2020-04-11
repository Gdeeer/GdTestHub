package com.gdeer.gdtesthub.generic.extendschange

/**
 * Kotlin 协变、逆变示例
 * out、in 在定义中出现
 */
class ChangeKotlin2 {
    open class A
    open class B : A()
    open class C : B()

    class AClassOut<out T> {
        private var mT: T? = null
        fun get(): T? {
            return mT
        }

        // 报错，T 不允许作为参数，除非加上 @UnsafeVariance 的注解
        //fun set(t: T) {
        //    mT = t
        //}
    }

    class AClassIn<in T> {
        private var mT: T? = null
        // 返回类型写 T? 会报错，不允许 T 作为返回类型
        fun get(): Any? {
            return mT
        }

        fun set(t: T) {
            mT = t
        }
    }

    fun main() {
        val aClass: AClassOut<String> = AClassOut()
        var aClass1: AClassOut<Any> = AClassOut()
        aClass1 = aClass // 正常

        val aClassAOut: AClassOut<A> = AClassOut()
        val aClassBOut: AClassOut<B> = AClassOut()
        val aClassCOut: AClassOut<C> = AClassOut()

        //// val aClassExtendB1: AClassOut<B> = aClassAOut // 报错
        val aClassExtendB2: AClassOut<B> = aClassBOut
        val aClassExtendB3: AClassOut<B> = aClassCOut
        val b2: B? = aClassExtendB2.get() // 可以 get 到 B
        //// aClassExtendB3.set(B()) // 报错，在定义期间就限制了没有这样的方法

        val aClassAIn: AClassIn<A> = AClassIn()
        val aClassBIn: AClassIn<B> = AClassIn()
        val aClassCIn: AClassIn<C> = AClassIn()

        val aClassSuperB1: AClassIn<B> = aClassAIn
        val aClassSuperB2: AClassIn<B> = aClassBIn
        //// val aClassSuperB3: AClassIn<B> = aClassCIn // 报错
        val o: Any? = aClassSuperB1.get() // 只能 get 到 Any
        //// aClassSuperB1.set(A()) // 报错，set 除 B 及其子类以外的类型就会报错
        aClassSuperB1.set(B())
        aClassSuperB1.set(C())
    }
}