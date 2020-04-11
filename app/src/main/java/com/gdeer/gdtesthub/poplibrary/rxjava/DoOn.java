package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;

/**
 * 监听 Observable 的生命周期回调
 */
public class DoOn {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .doOnSubscribe(s -> System.out.println("doOnSubscribe"))
            .doOnRequest(t -> System.out.println("doOnRequest: " + t))
            .doOnNext(integer -> System.out.println("doOnNext: " + integer))
            .doOnEach(integerNotification -> System.out.println("doOnEach: " + integerNotification))
            .doOnComplete(() -> System.out.println("doOnComplete"))
            .doOnTerminate(() -> System.out.println("doOnTerminate"))
            .doOnCancel(() -> System.out.println("doOnCancel"))
            .subscribe(System.out::println);
    }
}
