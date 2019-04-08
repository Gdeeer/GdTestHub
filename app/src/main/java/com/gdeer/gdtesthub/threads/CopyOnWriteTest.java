package com.gdeer.gdtesthub.threads;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteTest {

    static int i = 0;

    public static void main(String[] args) throws Exception {
        final List<String> list = new CopyOnWriteArrayList<>();
        for (int j = 0; j < 10000; j++) {
            list.add("");
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 10000; j++) {
                    list.remove(0);
                }
                System.out.println("thread 0: " + list.size());
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String s = list.get(list.size() - 1);
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        }).start();
    }
}
