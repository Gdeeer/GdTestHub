package com.gdeer.gdtesthub.java.protectedtest.package1;

/**
 * 同包下的类中，对 protected 方法的访问
 */
class AA {
    private void foo() {
        A a = new A();
        // 本包内的类可以访问 protected 修饰的方法
        a.getName();
    }
}
