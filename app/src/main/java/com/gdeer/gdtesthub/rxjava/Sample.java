package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 取样，发布指定间隔内，上游发布的最后一个产品（没有则不发布）
 */
public class Sample {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .sample(2, TimeUnit.SECONDS)
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
