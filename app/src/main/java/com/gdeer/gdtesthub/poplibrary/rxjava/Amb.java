package com.gdeer.gdtesthub.poplibrary.rxjava;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * 静态方法
 * 接受一个 Observable 序列，只发布其中最早发送产品或通知的 Observable 的产品
 */
public class Amb {
    public static void main(String[] args) {
        List<Flowable<String>> list = new ArrayList<>();
        list.add(Flowable.just("1"));
        list.add(Flowable.just("2"));
        list.add(Flowable.just("3"));
        Flowable.amb(list)
            .subscribe(s -> System.out.println(s));
    }
}
