package com.gdeer.gdtesthub.poplibrary.rxjava;

import io.reactivex.Flowable;

/**
 * 只取前面的几个
 */
public class Take {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3, 4, 5)
            .take(1)
            .subscribe(System.out::println);
    }
}
