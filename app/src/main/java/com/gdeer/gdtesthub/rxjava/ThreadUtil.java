package com.gdeer.gdtesthub.rxjava;

public class ThreadUtil {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep10s() {
        sleep(10000);
    }
}
