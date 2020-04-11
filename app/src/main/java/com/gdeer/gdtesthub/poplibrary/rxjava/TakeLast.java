package com.gdeer.gdtesthub.poplibrary.rxjava;

import io.reactivex.Flowable;

/**
 * 只取最后的几个
 */
public class TakeLast {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3, 4)
            .takeLast(1)
            .subscribe(System.out::println);
    }
}
