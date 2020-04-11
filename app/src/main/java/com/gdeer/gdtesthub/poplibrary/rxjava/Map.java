package com.gdeer.gdtesthub.poplibrary.rxjava;

import io.reactivex.Flowable;

/**
 * 把上游的产品转换为另一种产品，一个A转换为一个B，一对一
 */
public class Map {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3, 4)
            .map(integer -> String.valueOf(integer * 2))
            .subscribe(System.out::println);
    }
}
