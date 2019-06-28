package com.gdeer.gdtesthub.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * 跳过最后的几个产品，或跳过最后的一段时间（实际上是相当于延迟发布）
 */
public class SkipLast {
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .skipLast(1)
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long integer) throws Exception {
                    System.out.println(integer);
                }
            });

        ThreadUtil.sleep10s();
    }
}
