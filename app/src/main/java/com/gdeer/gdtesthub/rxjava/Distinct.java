package com.gdeer.gdtesthub.rxjava;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * 去重
 */
public class Distinct {
    public static void main(String[] args) {
        Flowable.just(1, 1, 2, 2)
            .distinct()
            .subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Exception {
                    System.out.println(integer);
                }
            });
    }
}
