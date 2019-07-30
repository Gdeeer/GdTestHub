package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;

/**
 * 使一个可连接 Observable 的行为与普通 Observable 一致
 * 在第一个 Observer subscribe 时自动 connect
 * 在最后一个 Observer unSubscribe 时自动 disConnect
 */
public class RefCount {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        ConnectableFlowable connectableFlowable =
            Flowable.interval(1, TimeUnit.SECONDS)
                .publish();

        Flowable ordinaryFlowable = connectableFlowable.refCount();
        ordinaryFlowable.subscribe(new Subscriber() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(300);
                Flowable.timer(3, TimeUnit.SECONDS)
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            System.out.println("first cancel");
                            s.cancel();
                        }
                    });
            }

            @Override
            public void onNext(Object o) {
                System.out.println("first onNext: " + o);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

        Flowable.timer(5, TimeUnit.SECONDS)
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    ordinaryFlowable.subscribe(new Consumer() {
                        @Override
                        public void accept(Object o) throws Exception {
                            System.out.println("second onNext: " + o);
                        }
                    });
                }
            });

        ThreadUtil.sleep10s();
    }
}
