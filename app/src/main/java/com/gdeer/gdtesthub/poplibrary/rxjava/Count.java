package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;

/**
 * 产品的总数
 */
public class Count {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .count()
            .subscribe(System.out::println);
    }
}
