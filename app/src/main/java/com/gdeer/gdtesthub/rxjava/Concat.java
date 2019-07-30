package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;

/**
 * 依次发布若干个 Observable
 */
public class Concat {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.concat(Flowable.just(1, 2, 3), Flowable.just(4, 5, 6))
            .subscribe(System.out::println);
    }
}
