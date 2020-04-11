package com.gdeer.gdtesthub.generic.extendschange

/**
 * Kotlin 协变、逆变示例
 * out、in 在使用中出现
 */
class ChangeKotlin {
    open class A
    open class B : A()
    open class C : B()

    class AClass<T> {
        private var mT: T? = null
        fun get(): T? {
            return mT
        }

        fun set(t: T) {
            mT = t
        }
    }

    fun <T> d(list: List<T>) {

    }

    fun main() {
        val aClass: AClass<String> = AClass()
        var aClass1: AClass<Any> = AClass()
        //// aClass1 = aClass // 报错

        val aClassA: AClass<A> = AClass()
        val aClassB: AClass<B> = AClass()
        val aClassC: AClass<C> = AClass()

        //// val aClassExtendB1: AClass<out B> = aClassA // 报错
        val aClassExtendB2: AClass<out B> = aClassB
        val aClassExtendB3: AClass<out B> = aClassC
        val b2: B? = aClassExtendB2.get() // 可以 get 到 B
        //// aClassExtendB2.set() // 报错，set 任何值都会报错

        val aClassSuperB1: AClass<in B> = aClassA
        val aClassSuperB2: AClass<in B> = aClassB
        //// val aClassSuperB3: AClass<in B> = aClassC // 报错
        val o: Any? = aClassSuperB1.get() // 只能 get 到 Any
        //// aClassSuperB1.set(A()) // 报错，set 除 B 及其子类以外的类型就会报错
        aClassSuperB1.set(B())
        aClassSuperB1.set(C())
    }
}