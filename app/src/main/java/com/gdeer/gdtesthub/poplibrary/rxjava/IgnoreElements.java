package com.gdeer.gdtesthub.poplibrary.rxjava;

import io.reactivex.Flowable;
import io.reactivex.functions.Action;

/**
 * 忽略产品，只发布结束信号
 */
public class IgnoreElements {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3, 4)
            .ignoreElements()
            .subscribe(new Action() {
                @Override
                public void run() throws Exception {
                    System.out.println("end");
                }
            });
    }
}
