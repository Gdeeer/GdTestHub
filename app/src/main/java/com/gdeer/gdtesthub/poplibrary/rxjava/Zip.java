package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 静态方法
 * 合并多个 Observable，全部 Observable 一起推进，全都有产品发布时才开始一起处理，对外发布
 */
public class Zip {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable<String> flowable1 = Flowable.interval(0, 2, TimeUnit.SECONDS).map(aLong -> "#" + aLong);
        Flowable<String> flowable2 = Flowable.interval(0, 1, TimeUnit.SECONDS).map(aLong -> "$" + aLong);
        Flowable.zip(flowable1, flowable2, (s, s2) -> s + s2)
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
