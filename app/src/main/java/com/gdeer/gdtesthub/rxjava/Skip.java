package com.gdeer.gdtesthub.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * 跳过几个产品，或跳过一段时间
 */
public class Skip {
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .skip(1)
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long integer) throws Exception {
                    System.out.println(integer);
                }
            });

        ThreadUtil.sleep10s();
    }
}
