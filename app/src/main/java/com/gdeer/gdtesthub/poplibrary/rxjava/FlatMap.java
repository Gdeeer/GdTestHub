package com.gdeer.gdtesthub.poplibrary.rxjava;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

/**
 * 更像是 MapFlat
 * 把上游的产品转换为另一种产品，一个A转换为多个B，一对多
 */
public class FlatMap {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3)
            .flatMap((Function<Integer, Publisher<String>>) integer -> new Flowable<String>() {
                @Override
                protected void subscribeActual(Subscriber<? super String> s) {
                    for (int i = 0; i < integer; i++) {
                        s.onNext(String.valueOf(integer));
                    }
                }
            })
            .subscribe(System.out::println);
    }
}
