package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

@SuppressLint("CheckResult")
public class From {
    public static void main(String[] args) {
        Flowable<String> flowable0 = Flowable.fromArray("1", "2", "3");
        flowable0.doOnComplete(System.out::println)
            .subscribe(System.out::print);

        String[] strs = new String[]{"1", "2", "3"};
        Flowable<String> flowable1 = Flowable.fromArray(strs);
        flowable1.doOnComplete(System.out::println)
            .subscribe(System.out::print);

        Flowable<String> flowable2 = Flowable.fromIterable(Arrays.asList(strs));
        flowable2.doOnComplete(System.out::println)
            .subscribe(System.out::print);

        Flowable<String> flowable3 = Flowable.fromPublisher(flowable2);
        flowable3.doOnComplete(System.out::println)
            .subscribe(System.out::print);

        Flowable<String> flowable4 = Flowable.fromCallable(() -> "123");
        flowable4.doOnComplete(System.out::println)
            .subscribe(System.out::print);

        ExecutorService executor1 = new ThreadPoolExecutor(0, 6,
            5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        Future<String> future = executor1.submit(() -> "123");
        Flowable<String> flowable5 = Flowable.fromFuture(future);
        flowable5.doOnComplete(System.out::println)
            .subscribe(System.out::print);
    }
}
