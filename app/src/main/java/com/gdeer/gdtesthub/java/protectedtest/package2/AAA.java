package com.gdeer.gdtesthub.java.protectedtest.package2;

import com.gdeer.gdtesthub.java.protectedtest.package1.A;

/**
 * 不同包下的类对 protected 方法的访问
 */
class AAA {
    private void foo() {
        A a = new A();
        // 报错，不同包的类中无法访问 protected 方法
        // a.getName();

        AAAA aaaa = new AAAA();
        // 报错，不同包的类中，即使是子类，也无法访问 protected 方法
        // aaaa.getName();
    }

    private static class AAAA extends A {
        private void foo() {
            // 子类中可以访问 protected 修饰的方法
            String name = getName();
        }
    }
}
