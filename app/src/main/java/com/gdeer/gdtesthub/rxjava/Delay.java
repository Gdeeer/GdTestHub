package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 收到上游的产品后，延迟一段时间，才开始发布
 */
public class Delay {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .delay(1, TimeUnit.SECONDS)
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
