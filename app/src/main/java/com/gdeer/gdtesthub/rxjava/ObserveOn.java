package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * 指定 Observer 接收产品的线程
 */
public class ObserveOn {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .observeOn(Schedulers.io())
            .subscribe(integer -> System.out.println("currentThread: " + Thread.currentThread()));
    }
}
