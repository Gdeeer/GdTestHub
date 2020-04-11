package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 把上游发布的产品收集后分批发布（一批满了才一起发布）
 */
public class Buffer {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .buffer(2)
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
