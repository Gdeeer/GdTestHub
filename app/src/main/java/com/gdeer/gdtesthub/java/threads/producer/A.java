package com.gdeer.gdtesthub.java.threads.producer;

import com.gdeer.gdtesthub.poplibrary.rxjava.ThreadUtil;

/**
 * 生产者消费者模型
 */
public class A {
    private static int count;
    private static final int MAX = 10;
    private static Object lock = "lock";

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer();
            producer.produce();
        }
        for (int i = 0; i < 3; i++) {
            Consumer consumer = new Consumer();
            consumer.consume();
        }
    }

    static class Producer {
        public void produce() {
            new Thread(() -> {
                while (true) {
                    ThreadUtil.sleep(200);
                    synchronized (lock) {
                        while (count >= MAX) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        count++;
                        System.out.println("produceTo: " + count);
                        lock.notifyAll();
                    }
                }
            }).start();
        }
    }

    static class Consumer {
        public void consume() {
            new Thread(() -> {
                while (true) {
                    ThreadUtil.sleep(200);
                    synchronized (lock) {
                        while (count <= 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        count--;
                        System.out.println("consumeTo: " + count);
                        lock.notifyAll();
                    }
                }
            }).start();
        }
    }
}
