package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 合并多个 Observable，用一个 Observable 来统一发布它们的产品
 */
public class Merge {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable<String> flowable1 = Flowable.interval(0, 2, TimeUnit.SECONDS).map(aLong -> "#" + aLong);
        Flowable<String> flowable2 = Flowable.interval(1, 2, TimeUnit.SECONDS).map(aLong -> "$" + aLong);
        Flowable.merge(flowable1, flowable2)
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
