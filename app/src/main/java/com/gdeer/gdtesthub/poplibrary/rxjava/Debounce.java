package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 收到上游的产品，一段时间内没有收到其他产品，才把这个产品发布出去
 */
public class Debounce {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.intervalRange(0, 3, 0, 1, TimeUnit.MILLISECONDS)
            .debounce(2, TimeUnit.SECONDS)
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
