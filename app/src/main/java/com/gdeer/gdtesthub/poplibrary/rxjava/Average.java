package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.Flowable;

/**
 * 静态方法，产品的平均值
 */
public class Average {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        MathFlowable.averageFloat(Flowable.just(1, 2, 3))
            .subscribe(System.out::println);
    }
}
