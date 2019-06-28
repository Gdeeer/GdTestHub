package com.gdeer.gdtesthub.rxjava;

import io.reactivex.Flowable;

/**
 * 发布几个特定的产品
 * 最原始的发布
 */
public class Just {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .subscribe(integer -> System.out.println(integer));
        ThreadUtil.sleep(1000);
    }
}
