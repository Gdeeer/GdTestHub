package com.gdeer.gdtesthub.rxjava;

import android.annotation.SuppressLint;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;

/**
 * 把一个普通 Observable 转换为可连接 Observable
 * 可连接的 Observable:
 * 在 connect 后开始发布
 * 一次 connect 只会发布一次，不管期间有没有 Observer、Observer 在何时 subscribe
 * 一次 connect 后不能再次 connect
 * 一次 disConnect 后可以再次 connect
 * 再次 connect 后会重头开始发布*
 * disConnect:
 * 调用 connect 的 dispose，或自然结束
 */
public class Publish {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        ConnectableFlowable flowable =
            Flowable.interval(1, TimeUnit.SECONDS)
                .take(60)
                .publish();
        flowable.connect();

        Flowable.timer(2, TimeUnit.SECONDS)
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {

                    flowable.subscribe(new Subscriber() {
                        @Override
                        public void onSubscribe(Subscription s) {
                            System.out.println("first onSubscribe");
                            s.request(1000);
                            Flowable.timer(2, TimeUnit.SECONDS)
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(Long aLong) throws Exception {
                                        System.out.println("cancel");
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
                }
            });

        Flowable.timer(6, TimeUnit.SECONDS)
            .subscribe(new Consumer<Long>() {
                @Override
                public void accept(Long aLong) throws Exception {
                    flowable.subscribe(new Subscriber() {
                        @Override
                        public void onSubscribe(Subscription s) {
                            System.out.println("second onSubscribe");
                            s.request(1000);
                        }

                        @Override
                        public void onNext(Object o) {
                            System.out.println("second onNext: " + o);
                        }

                        @Override
                        public void onError(Throwable t) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

                }
            });

        ThreadUtil.sleep10s();
    }
}
