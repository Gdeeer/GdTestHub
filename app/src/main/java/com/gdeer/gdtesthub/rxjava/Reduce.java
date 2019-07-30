package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;

/**
 * 可以对上游的产品进行叠加操作（如累加、累乘），再将最终结果发布出去
 */
public class Reduce {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .reduce((integer, integer2) -> integer + integer2)
            .subscribe(System.out::println);
    }
}
