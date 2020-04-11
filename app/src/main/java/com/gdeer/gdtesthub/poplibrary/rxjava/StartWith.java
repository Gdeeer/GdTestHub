package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;

/**
 * 在上游发布前先发布几个产品
 */
public class StartWith {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .startWith(0)
            .subscribe(System.out::println);
    }
}
