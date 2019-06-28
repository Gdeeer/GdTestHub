package com.gdeer.gdtesthub.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * 把上游的产品分批，每批由不同的 Observable 发布（按时间线依次发布）
 */
public class Window {
    public static void main(String[] args) {
        Flowable.interval(1, TimeUnit.SECONDS)
            .window(3)
            .subscribe(new Consumer<Flowable<Long>>() {
                @Override
                public void accept(Flowable<Long> longFlowable) throws Exception {
                    longFlowable.subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            System.out.println(longFlowable + " " +aLong);
                        }
                    });
                }
            });

        ThreadUtil.sleep10s();
    }
}
