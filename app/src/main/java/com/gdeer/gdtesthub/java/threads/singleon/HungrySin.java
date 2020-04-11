package com.gdeer.gdtesthub.java.threads.singleon;

/**
 * 饿汉，即比较饥饿，一上来就开吃（生成单例）
 * 即在 getInstance() 前就生成单例
 */
public class HungrySin {
    private static HungrySin sInstance = new HungrySin();

    static {
        // 或把赋值放入 static 块中
        // sInstance = new HungrySin();
    }

    private HungrySin() {

    }

    public static HungrySin getInstance() {
        return sInstance;
    }
}
