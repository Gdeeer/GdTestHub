package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Notification;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * materialize：把 onNext、onComplete、onError 都包装为 Notification 发布出去
 * deMaterialize：把 Notification 解包装为 onNext、onComplete、onError
 */
public class Materialize {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {

        PublishSubject<Notification<Integer>> publishSubject = PublishSubject.create();
        publishSubject
            .dematerialize()
            .subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Object o) {
                    System.out.println("onNext " + o);
                }

                @Override
                public void onError(Throwable e) {
                    System.out.println("onError " + e);
                }

                @Override
                public void onComplete() {

                }
            });

        Flowable.create((FlowableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onError(new OutOfMemoryError());
        }, BackpressureStrategy.BUFFER)
            .materialize()
            .subscribe(new Consumer<Notification<Integer>>() {
                @Override
                public void accept(Notification<Integer> integerNotification) throws Exception {
                    publishSubject.onNext(integerNotification);
                }
            });

        ThreadUtil.sleep10s();
    }
}
