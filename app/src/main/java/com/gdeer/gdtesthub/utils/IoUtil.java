package com.gdeer.gdtesthub.utils;

import java.io.Closeable;

public class IoUtil {

    public static void closeSilently(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            closeSilently(closeable);
        }
    }

    public static void closeSilently(Closeable closeable) {
        try {
            closeable.close();
        } catch (Exception var2) {
            var2.printStackTrace();
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
