package com.gdeer.gdtesthub.java.threads.singleon;

/**
 * 使用枚举
 * 能防止反序列化创建新的对象
 */
public enum HungryEnumSin {
    sInstance;

    public static void main(String[] args) {
        System.out.println(HungryEnumSin.sInstance);
    }
}
