package com.gdeer.gdtesthub.poplibrary.rxjava;


import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

public class MyScheduler extends Scheduler {
    @Override
    public Worker createWorker() {
        return new MyWorker();
    }

    class MyWorker extends Worker {

        @Override
        public Disposable schedule(Runnable run, long delay, TimeUnit unit) {
            run.run();
            return null;
        }

        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }
    }

    class MyTask implements Runnable, Disposable {
        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }

        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {
        MyScheduler myScheduler = new MyScheduler();
        myScheduler.scheduleDirect(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        });
    }
}
