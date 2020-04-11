package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 跳过上游的产品，直到第二个 Observable 开始发布；随后都正常发布
 */
public class SkipUntil {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .skipUntil(Flowable.timer(2, TimeUnit.SECONDS))
            .doOnComplete(() -> System.out.println("onComplete"))
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
