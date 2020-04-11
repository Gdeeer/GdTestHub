package com.gdeer.gdtesthub.generic.extendschange;


/**
 * Java 协变、逆变示例
 */
public class ChangeJava {
    class A {
    }

    class B extends A {
    }

    class C extends B {
    }

    class AClass<T> {
        private T mT;

        void set(T u) {
            mT = u;
        }

        T get() {
            return mT;
        }
    }

    private void doSomething() {
        AClass<String> aClass = new AClass<>();
        AClass<Object> aClass1 = new AClass<>();
        //// aClass1 = aClass; // 报错

        AClass<A> aClassA = new AClass<>();
        AClass<B> aClassB = new AClass<>();
        AClass<C> aClassC = new AClass<>();

        //// AClass<? extends B> aClassExtendB1 = aClassA; // 报错
        AClass<? extends B> aClassExtendB2 = aClassB;
        AClass<? extends B> aClassExtendB3 = aClassC;
        B b2 = aClassExtendB2.get(); // 可以 get 到 B
        //// aClassExtendB2.set(); // 报错，set 任何值都会报错

        AClass<? super B> aClassSuperB1 = aClassA;
        AClass<? super B> aClassSuperB2 = aClassB;
        //// AClass<? super B> aClassSuperB3 = aClassC; // 报错
        Object o = aClassSuperB1.get(); // 只能 get 到 Object
        //// aClassSuperB1.set(new A()); // 报错，set 除 B 及其子类以外的类型就会报错
        aClassSuperB2.set(new B());
        aClassSuperB2.set(new C());
    }
}

