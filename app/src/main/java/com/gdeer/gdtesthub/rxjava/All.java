package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;

/**
 * 是否所有产品都满足某个条件
 */
public class All {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .all(integer -> integer > 0)
            .subscribe(System.out::println);
    }
}
