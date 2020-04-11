package com.gdeer.gdtesthub.java.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableTest {
    static int val = 0;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // new Thread 用法
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                val = 2;
                System.out.println("runnable 计算完毕：" + val);
            }
        };
        new Thread(runnable).start();

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 2;
            }
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        val = futureTask.get();
        System.out.println("callable 计算完毕：" + val);


        // ExecutorService 用法
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(runnable);

        ExecutorService executor1 = Executors.newCachedThreadPool();
        Future<Integer> future = executor1.submit(callable);
        val = future.get();
        System.out.println("callable 计算完毕：" + val);
    }
}