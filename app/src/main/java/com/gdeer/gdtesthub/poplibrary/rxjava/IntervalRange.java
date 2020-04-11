package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 以固定的间隔发布指定头尾的一串连续整数
 */
public class IntervalRange {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.intervalRange(4, 4, 0, 1, TimeUnit.SECONDS)
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
