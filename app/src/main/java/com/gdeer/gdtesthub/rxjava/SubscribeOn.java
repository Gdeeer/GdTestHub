package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * 指定 subscribe 发生的线程，即源 Observable 生产产品的线程
 */
public class SubscribeOn {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable<Integer> flowable = new Flowable<Integer>() {
            @Override
            protected void subscribeActual(Subscriber s) {
                System.out.println("subscribeActual currentThread: " + Thread.currentThread());
                s.onNext(1);
                s.onNext(2);
                s.onNext(3);
            }
        };
        flowable
            .subscribeOn(Schedulers.newThread())
            .observeOn(Schedulers.newThread())
            .subscribe(integer -> System.out.println("currentThread: " + Thread.currentThread()));

        ThreadUtil.sleep10s();
    }
}
