package com.gdeer.gdtesthub.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * 把上游的发布过程重复n遍，（有过程后才能重复，即要用在生产出 Observable 后才能使用 Repeat；过程完了之后才能重复）
 */
public class Repeat {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Exception {
                    System.out.println(integer);
                }
            });

        Flowable.interval(1, TimeUnit.SECONDS)
            .repeat(3)
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    System.out.println(aLong);

                }
            });

        ThreadUtil.sleep10s();
    }
}
