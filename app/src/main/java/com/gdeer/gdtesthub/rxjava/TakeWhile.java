package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 发布上游的产品，直到产品不满足某个条件；随后不再发布
 */
public class TakeWhile {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .takeWhile(a -> a < 3)
            .doOnComplete(() -> System.out.println("onComplete"))
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
