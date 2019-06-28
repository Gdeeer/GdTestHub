package com.gdeer.gdtesthub.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * 一段时间后发送一个 0L
 */
public class Timer {
    public static void main(String[] args) throws InterruptedException {
        Flowable.timer(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println(aLong);
            }
        });

        ThreadUtil.sleep(1000);
    }
}
