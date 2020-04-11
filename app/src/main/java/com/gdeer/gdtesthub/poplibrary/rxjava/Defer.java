package com.gdeer.gdtesthub.poplibrary.rxjava;

import org.reactivestreams.Publisher;

import android.annotation.SuppressLint;

import java.util.concurrent.Callable;

import io.reactivex.Flowable;

/**
 * 当被 subscribe 后，才创建 Observable，对每个 Observer 都创建各自的 Observable
 */
@SuppressLint("checkResult")
public class Defer {
    public static void main(String[] args) {
        Flowable flowable = Flowable.defer(new Callable<Publisher<?>>() {
            @Override
            public Publisher<String> call() throws Exception {
                return Flowable.just("1", "2");
            }
        });
        flowable.subscribe(System.out::println);
    }
}
