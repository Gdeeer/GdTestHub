package com.gdeer.gdtesthub.java.extend;

/**
 * 子类必须调用父类的构造函数
 * 因为子类无法初始化父类的私有变量
 */
public class superInvoke {
    public static void main(String[] args) {
        new B(1);
        new D(1);
    }


    //------------------------------------------------------------------------

    private static class A {
    }

    private static class B extends A {
        B(int number) {
            // 父类有「无参构造函数」时，子类的构造函数无需调用父类的构造函数
            // 编译器会自己加上 super()
            System.out.println("B:" + number);
        }
    }

    //------------------------------------------------------------------------

    private static class C {
        C(int number) {
            System.out.println("C: number: " + number);
        }
    }

    private static class D extends C {
        D(int number) {
            // 父类无「无参构造函数」时，子类的构造函数必须调用父类的构造函数
            super(1);
            System.out.println("D:" + number);
        }
    }
}
