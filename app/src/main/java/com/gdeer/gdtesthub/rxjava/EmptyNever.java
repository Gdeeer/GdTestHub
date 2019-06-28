package com.gdeer.gdtesthub.rxjava;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;

/**
 * empty：不发布产品，直接宣告结束
 * never：不发布产品，不宣告结束
 */
public class EmptyNever {
    public static void main(String[] args) {
        Subscriber<Object> subscriber = new Subscriber<Object>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext");
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };
        Flowable.empty()
            .doOnSubscribe(s -> System.out.println("empty:"))
            .subscribe(subscriber);
        Flowable.never()
            .doOnSubscribe(s -> System.out.println("never:"))
            .subscribe(subscriber);
    }
}
