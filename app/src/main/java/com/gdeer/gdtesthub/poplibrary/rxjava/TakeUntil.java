package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 发布上游的产品，直到第二个 Observable 开始发布，或产品满足了某个条件；随后不再发布
 */
public class TakeUntil {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .takeUntil(a -> a > 3)
            .doOnComplete(() -> System.out.println("onComplete"))
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
