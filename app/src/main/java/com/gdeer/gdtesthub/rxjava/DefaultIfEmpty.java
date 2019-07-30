package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;

/**
 * 如果上游的 Observable 为空，则发布一个默认产品
 */
public class DefaultIfEmpty {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.empty()
            .defaultIfEmpty(5)
            .subscribe(System.out::println);
    }
}
