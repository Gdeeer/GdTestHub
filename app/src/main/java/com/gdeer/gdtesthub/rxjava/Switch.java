package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

public class Switch {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable<Flowable<Long>> flowable = Flowable.interval(0, 1, TimeUnit.SECONDS)
            .map(Flowable::just);
        Flowable.switchOnNext(flowable)
            .doOnSubscribe(subscription -> System.out.println("onSubscribe"))
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
