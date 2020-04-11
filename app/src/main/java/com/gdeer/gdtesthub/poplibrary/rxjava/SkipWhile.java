package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Predicate;

/**
 * 跳过上游的产品，直到产品不满足某个条件；随后都正常发布
 */
public class SkipWhile {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .skipWhile(new Predicate<Long>() {
                @Override
                public boolean test(Long aLong) throws Exception {
                    return aLong < 3;
                }
            })
            .doOnComplete(() -> System.out.println("onComplete"))
            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
