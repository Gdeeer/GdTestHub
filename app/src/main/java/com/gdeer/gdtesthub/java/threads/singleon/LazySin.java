package com.gdeer.gdtesthub.java.threads.singleon;

/**
 * 懒汉，即不急着吃，必须得吃了的时候才吃（生成单例）
 * 即在 getInstance() 时才生成单例
 */
public class LazySin {
    private static volatile LazySin sInstance;

    private LazySin() {
    }

    /**
     * 1
     * 双重判空，避免无谓的上锁解锁
     */
    public static LazySin getInstance1() {
        if (sInstance == null) {
            synchronized (LazySin.class) {
                if (sInstance == null) {
                    sInstance = new LazySin();
                }
            }
        }
        return sInstance;
    }

    /**
     * 2
     * 给方法的内容加上锁
     */
    public static LazySin getInstance2() {
        synchronized (LazySin.class) {
            if (sInstance == null) {
                sInstance = new LazySin();
            }
        }
        return sInstance;
    }

    /**
     * 3
     * 给整个方法加上锁，与 2 效果完全一致
     */
    public static synchronized LazySin getInstance3() {
        if (sInstance == null) {
            sInstance = new LazySin();
        }
        return sInstance;
    }
}
