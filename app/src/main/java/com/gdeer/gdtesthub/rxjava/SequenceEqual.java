package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;

/**
 * 静态方法
 * 判断两个 Observable 发布的产品序列是否相同（一一对应）
 */
public class SequenceEqual {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.sequenceEqual(Flowable.just(1, 2, 3), Flowable.just(1, 2, 3))
            .subscribe(System.out::println);
    }
}
