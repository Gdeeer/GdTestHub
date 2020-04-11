package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * 静态方法
 * 合并多个 Observable，任一 Observable 发布产品时，将这个产品跟其他 Observable 的最新产品一起处理后发布
 * 当所有 Observable 都有产品发布后才开始一起处理、发布
 * 挺绕的，看看官网的图吧
 */
public class CombineLatest {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        Flowable flowable1 = Flowable.just("1", "2", "3");
        Flowable flowable2 = Flowable.just("6", "7", "8");
        Flowable flowable3 = Flowable.just("啊", "吧", "从");
        Flowable[] flowables = new Flowable[]{flowable1, flowable2, flowable3};
        Flowable.combineLatest(flowables, new Function<Object[], String>() {
            @Override
            public String apply(Object[] objects) throws Exception {
                return (String) objects[0] + objects[1] + objects[2];
            }
        })
            .subscribe(System.out::println);
    }
}
