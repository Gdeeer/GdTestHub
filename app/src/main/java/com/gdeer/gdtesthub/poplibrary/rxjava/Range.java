package com.gdeer.gdtesthub.poplibrary.rxjava;

import io.reactivex.Flowable;

/**
 * 发布一串连续的整数
 */
public class Range {
    public static void main(String[] args) {
        Flowable.range(1, 10)
            .subscribe(System.out::println);
    }
}
