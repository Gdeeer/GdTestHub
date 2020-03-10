package com.gdeer.gdtesthub.threads.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS 测试
 */
public class CasTest {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger();
        // 通过 CAS 实现，比较&交换，一个 cpu 指令
        integer.addAndGet(1);
        System.out.println(integer.get());
    }
}
