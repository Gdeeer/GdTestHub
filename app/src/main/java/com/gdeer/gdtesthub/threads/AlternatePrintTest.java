package com.gdeer.gdtesthub.threads;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AlternatePrintTest {
    static int i = 0;
    static Object sObject = 1;


    static class A {
        Object a(int a) {
            return 0;
        }
    }

    static class B extends A {
        @Override
        public String a(int a) {
            return "";
        }

        public String a(String a) {
            return "";
        }
    }


    public static void main(String[] args) {
        A a = new A();
        B b = new B();
        String s = (String) b.a(1);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (sObject) {
                    while (i % 2 == 0) {
                        try {
                            Thread.sleep(200);
                            i++;
                            System.out.println(i);
                            sObject.notifyAll();
                            sObject.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (sObject) {
                    while (i % 2 != 0) {
                        try {
                            Thread.sleep(200);
                            i++;
                            System.out.println(i);
                            sObject.notifyAll();
                            sObject.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
