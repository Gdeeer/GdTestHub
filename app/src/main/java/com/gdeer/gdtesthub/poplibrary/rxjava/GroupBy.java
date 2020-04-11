package com.gdeer.gdtesthub.poplibrary.rxjava;

import io.reactivex.Flowable;
import io.reactivex.flowables.GroupedFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 把上游的产品按某个标准分组，下游拿到的产品是 Observable，需要再 subscribe 后才能继续分发（按时间线依次发布）
 */
public class GroupBy {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3, 4, 5, 6, 1, 2)
            .groupBy(new Function<Integer, String>() {
                @Override
                public String apply(Integer integer) throws Exception {
                    return String.valueOf(integer / 2);
                }
            })
            .subscribe(new Consumer<GroupedFlowable<String, Integer>>() {
                @Override
                public void accept(GroupedFlowable<String, Integer> stringIntegerGroupedFlowable) throws Exception {
                    stringIntegerGroupedFlowable
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                System.out.println(stringIntegerGroupedFlowable.getKey() + ": " + integer);
                            }
                        });
                }
            });
    }
}