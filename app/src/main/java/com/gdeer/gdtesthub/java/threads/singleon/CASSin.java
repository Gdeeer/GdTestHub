package com.gdeer.gdtesthub.java.threads.singleon;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 使用 CAS，即通过一次比较和交换，来最终确定一个实例
 */
public class CASSin {
    private static final AtomicReference<CASSin> SIN_ATOMIC_REFERENCE = new AtomicReference<>();

    private CASSin() {

    }

    private static CASSin getInstance() {
        while (true) {
            if (SIN_ATOMIC_REFERENCE.get() != null) {
                return SIN_ATOMIC_REFERENCE.get();
            }

            CASSin sin = new CASSin();
            if (SIN_ATOMIC_REFERENCE.compareAndSet(null, sin)) {
                // 如果多个线程执行到这里，只有一个线程会在这里返回 true
                return sin;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(CASSin.getInstance());
    }
}
