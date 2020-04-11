package com.gdeer.gdtesthub.poplibrary.rxjava;

import android.annotation.SuppressLint;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;

/**
 * 把一个普通 Observable 转换为特殊可连接的 Observable
 * connect 后，对每个 Observer 都发送相同的产品序列，如果它 subscribe 得晚的话，把错过的先补给它
 */
public class Replay {
    @SuppressLint("CheckResult")
    public static void main(String[] args) {
        ConnectableFlowable connectableFlowable =
            Flowable.interval(1, TimeUnit.SECONDS)
                .replay();

        connectableFlowable.connect();

        connectableFlowable.subscribe(new Subscriber() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("first onSubscribe");
                s.request(1000);
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

        Flowable.timer(3, TimeUnit.SECONDS)
            .subscribe(aLong -> connectableFlowable.subscribe(new Subscriber<Long>() {
                @Override
                public void onSubscribe(Subscription s) {
                    System.out.println("second onSubscribe");
                    s.request(1000);
                }

                @Override
                public void onNext(Long aLong) {
                    System.out.println("second onNext: " + aLong);
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onComplete() {

                }
            }));

        ThreadUtil.sleep10s();
    }
}
