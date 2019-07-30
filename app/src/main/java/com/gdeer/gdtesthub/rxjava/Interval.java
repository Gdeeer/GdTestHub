package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 以固定的间隔发布从 0 开始的一串连续整数（默认在间隔后才开始发布，指定 initialDelay 为 0 则立即开始）
 */
@SuppressLint("CheckResult")
public class Interval {
    public static void main(String[] args) {
        Subscriber<Object> subscriber = new PrintSubscriber();
        Flowable flowable = Flowable.interval(1, TimeUnit.SECONDS);
        flowable.subscribe(o -> System.out.println("first: " + o));
        Flowable.timer(2, TimeUnit.SECONDS)
            .subscribe(aLong -> flowable.subscribe(o -> System.out.println("second: " + o)));


        ThreadUtil.sleep10s();
    }

}
