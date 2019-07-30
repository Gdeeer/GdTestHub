package com.gdeer.gdtesthub.rxjava;

import org.reactivestreams.Publisher;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 静态方法
 * 接受三个方法，一个 Resource 工厂，一个 Observable 工厂，一个 Resource 的 dispose 方法
 * Resource 是 Observable 会用到的资源，和 Observable 的存活时间相同，在 Observable terminate 时会 dispose 这个 Resource
 * 类似一个 try/finally，在 dispose 方法中做回收工作
 */
public class Using {
    public static void main(String[] args) {
        Flowable f = Flowable.interval(1, TimeUnit.SECONDS);
        Flowable.using(new Callable<TimeIt>() {
            @Override
            public TimeIt call() throws Exception {
                return new TimeIt("Gdeer");
            }
        }, new Function<TimeIt, Publisher<?>>() {
            @Override
            public Publisher<?> apply(TimeIt timeIt) throws Exception {
                return f;
            }
        }, new Consumer<TimeIt>() {
            @Override
            public void accept(TimeIt timeIt) throws Exception {
                timeIt.dispose();
            }
        })
            .take(3)
            .subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object o) throws Exception {
                    System.out.println(o);
                }
            });

        ThreadUtil.sleep10s();
    }


    static class TimeIt implements Disposable {

        private String name;
        private long startTime;
        private boolean isDisposed;

        public TimeIt(String name) {
            this.name = name;
            this.startTime = System.currentTimeMillis();
        }

        @Override
        public void dispose() {
            long curTime = System.currentTimeMillis();
            isDisposed = true;
            System.out.println(name + " takes " + (curTime - startTime));
        }

        @Override
        public boolean isDisposed() {
            return isDisposed;
        }
    }
}
