package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * 转化 Obserable 到其他结构
 */
public class To {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .toList()
            .subscribe(System.out::println);

        Flowable.just(1, 2, 3)
            .toMap((Function<Integer, Object>) integer -> integer * integer)
            .subscribe(System.out::println);

        Flowable.just(1, 3, 2)
            .toSortedList()
            .subscribe(System.out::println);
    }
}
