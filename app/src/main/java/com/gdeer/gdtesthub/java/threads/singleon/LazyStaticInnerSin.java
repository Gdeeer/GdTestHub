package com.gdeer.gdtesthub.java.threads.singleon;

/**
 * 使用静态内部类的懒汉
 * 也可以实现延迟加载
 */
public class LazyStaticInnerSin {

    private LazyStaticInnerSin() {

    }

    private static class A {
        static LazyStaticInnerSin sInstance = new LazyStaticInnerSin();
    }

    public static LazyStaticInnerSin getInstance() {
        return A.sInstance;
    }
}
