package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import hu.akarnokd.rxjava2.math.MathFlowable;
import io.reactivex.Flowable;

public class Min {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        MathFlowable.min(Flowable.just(1, 2, 3))
            .subscribe(System.out::println);
    }
}
