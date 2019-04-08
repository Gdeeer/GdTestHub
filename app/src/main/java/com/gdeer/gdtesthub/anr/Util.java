package com.gdeer.gdtesthub.anr;

import android.os.SystemClock;

public class Util {
    public static synchronized void get() {
        SystemClock.sleep(100000);
    }
}
