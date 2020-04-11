package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 静态方法
 * 处理一个发布子 Observable 的源 Observable，源 Observable 每发布一个新的子 Observable，则开始对外发布这个子 Observable 的产品
 * 同时取消对之前的子 Observable 的订阅
 */
public class SwitchOnNext {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable<Flowable<Long>> flowable = Flowable.interval(0, 3, TimeUnit.SECONDS)
            .map(aLong -> Flowable.interval(0, 1, TimeUnit.SECONDS));
        Flowable.switchOnNext(flowable)
            .doOnSubscribe(subscription -> System.out.println("onSubscribe"))
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
