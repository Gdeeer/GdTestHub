package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 发布一个 Timed 产品，它包装了原产品和该产品距离上一个产品发布的时间间隔
 */
public class TimeInterval {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .timeInterval()
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
