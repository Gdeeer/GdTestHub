package com.gdeer.gdtesthub.poplibrary.rxjava;

import io.reactivex.Flowable;

/**
 * 可以对上游的产品进行叠加操作（如累加、累乘），再依次发布出去
 */
public class Scan {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3, 4, 5)
            .scan((integer, integer2) -> integer * integer2)
            .subscribe(System.out::println);
    }
}
