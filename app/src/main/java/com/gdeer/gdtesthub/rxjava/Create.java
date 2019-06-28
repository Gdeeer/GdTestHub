package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;

/**
 * 手动调用 onNext()
 */
@SuppressLint("checkResult")
public class Create {
    public static void main(String[] args) {
        Flowable flowable = Flowable.create(emitter -> {
            emitter.onNext(0);
            emitter.onNext(1);
            emitter.onNext(2);
        }, BackpressureStrategy.BUFFER);

        flowable.subscribe(System.out::println);
    }
}
