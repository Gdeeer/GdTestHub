package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * 设置产品的发布间隔，超时后发出 onError
 */
public class TimeOut {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .map((Function<Long, Flowable>) aLong ->
                Flowable.just(aLong).delay(aLong, TimeUnit.SECONDS))
            .subscribe(flowable -> flowable
                .timeout(2, TimeUnit.SECONDS)
                .subscribe(o -> System.out.println(o),
                    throwable -> System.out.println(throwable)));

        ThreadUtil.sleep10s();
    }
}
