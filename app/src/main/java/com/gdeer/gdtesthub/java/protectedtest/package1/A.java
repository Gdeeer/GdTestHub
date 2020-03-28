package com.gdeer.gdtesthub.java.protectedtest.package1;

public class A {
    /**
     * 访问修饰符是 protected，同包内、子类可以访问
     */
    protected String getName() {
        return "";
    }

    private class B extends A {
        /**
         * 子类中可以重写 getName，也可以扩大方法的修饰符
         * 不能缩小，要满足里式替换原则：所以用父类的地方都可以换成子类
         */
        @Override
        public String getName() {
            return "";
        }
    }
}
