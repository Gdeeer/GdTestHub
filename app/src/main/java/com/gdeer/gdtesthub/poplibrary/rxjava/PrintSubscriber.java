package com.gdeer.gdtesthub.poplibrary.rxjava;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

class PrintSubscriber implements Subscriber<Object> {
    @Override
    public void onSubscribe(Subscription s) {
        System.out.println("onSubscribe");
    }

    @Override
    public void onNext(Object o) {
        System.out.println("onNext " + o);
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("onError " + t);
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");
    }
}
