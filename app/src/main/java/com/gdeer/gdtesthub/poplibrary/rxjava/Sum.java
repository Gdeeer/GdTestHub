package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.Flowable;

/**
 * 产品的累加值
 */
public class Sum {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        MathFlowable.sumInt(Flowable.just(1, 2, 3))
            .subscribe(System.out::println);
    }
}
