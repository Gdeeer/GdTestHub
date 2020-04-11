package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;

/**
 * onError 之后重新 subscribe n 次
 */
public class Retry {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.create((FlowableOnSubscribe<String>) emitter -> {
            emitter.onNext("a");
            emitter.onNext("b");
            emitter.onError(new NullPointerException());
        }, BackpressureStrategy.BUFFER)
            .retry(1)
            .subscribe(System.out::println);
    }
}
