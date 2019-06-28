package com.gdeer.gdtesthub.rxjava;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

/**
 * 合并两个 Observable：left、right，任何一个发布产品时，将这个产品和它所处 window 区间所属的产品合并后发布
 * 每个产品会有一个属于自己的 window
 * 每个产品可能处于多个 window 区间内
 * window 的开启：left、right 中有产品发布，就会开启一个该产品对应的 window
 * window 的关闭：产品对应 window 的关闭由它的 leftEnd、rightEnd 两个 Observable 决定，leftEnd、rightEnd 发布一个产品或宣布结束，就会关闭它的 window
 */
public class Join {
    public static void main(String[] args) {
        Flowable<String> flowable1 = Flowable.interval(0, 2, TimeUnit.SECONDS).map(aLong -> "#" + aLong);
        Flowable<String> flowable2 = Flowable.interval(1, 2, TimeUnit.SECONDS).map(aLong -> "$" + aLong);
        flowable1
            .join(flowable2,
                integer -> Flowable.timer(2, TimeUnit.SECONDS),
                tRight -> Flowable.timer(2, TimeUnit.SECONDS),
                (integer, integer2) -> integer + integer2)
            .doOnSubscribe(s -> System.out.println("both end after 2 second:"))
            .subscribe(System.out::println);

//        flowable1
//            .join(flowable2,
//                integer -> Flowable.never(),
//                tRight -> Flowable.never(),
//                (integer, integer2) -> integer + integer2)
//            .doOnSubscribe(s -> System.out.println("both never end:"))
//            .subscribe(System.out::println);

//        flowable1
//            .join(flowable2,
//                integer -> Flowable.never(),
//                tRight -> Flowable.empty(),
//                (integer, integer2) -> integer + integer2)
//            .doOnSubscribe(s -> System.out.println("left never end, right never start:"))
//            .subscribe(System.out::println);

//        flowable1
//            .join(flowable2,
//                integer -> Flowable.empty(),
//                tRight -> Flowable.never(),
//                (integer, integer2) -> integer + integer2)
//            .doOnSubscribe(s -> System.out.println("left never start, right never end:"))
//            .subscribe(System.out::println);

        ThreadUtil.sleep10s();
    }
}
