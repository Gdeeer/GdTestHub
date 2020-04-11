package com.gdeer.gdtesthub.poplibrary.rxjava;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 把上游的产品转换为另一种产品
 * 上游每秒生产一个产品，A、B...
 * A 生产后每秒生产一个 A1、A2...
 * B 生产后每秒生产一个 B1、B2...
 * 如果是 flatMap，B 生产后，A1、A2... 的生产不受影响，即同时可以有多个原产品的衍生产品在生产
 * 如果是 switchMap，B 生产后，A1、A2... 的生产就会停止，即同事只有一个原产品的衍生产品在生产
 *
 * 官网的对应图示跟 flatMap 一样，只是要去掉第二个绿色菱形；因为原产品的蓝的已经发布，绿的就失效了
 */
public class SwitchMap {
    /**
     * 每 4s 生产一个 0、1、2...
     */
    public static void main(String[] args) {
        Flowable.interval(0, 4, TimeUnit.SECONDS)
                .switchMap(new Function<Long, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(Long llong) throws Exception {
                        System.out.println("apply "+ llong);
                        return Flowable.timer(1, TimeUnit.SECONDS)
                                .map(aLong -> aLong + llong)
                                .repeat(1000);
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        System.out.println(o);
                    }
                });

        ThreadUtil.sleep(20000);
    }
}