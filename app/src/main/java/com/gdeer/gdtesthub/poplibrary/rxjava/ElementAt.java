package com.gdeer.gdtesthub.poplibrary.rxjava;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class ElementAt {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3, 4)
            .elementAt(2)
            .subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Exception {
                    System.out.println(integer);
                }
            });
    }
}
