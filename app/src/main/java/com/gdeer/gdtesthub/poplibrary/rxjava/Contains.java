package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

/**
 * 上游的产品中是否包含某个特定产品
 */
public class Contains {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .contains(3)
            .subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    System.out.println(aBoolean);
                }
            });
    }
}
