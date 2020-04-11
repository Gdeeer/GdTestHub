package com.gdeer.gdtesthub.poplibrary.rxjava;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class First {
    public static void main(String[] args) {
        Flowable.just(1, 2, 3, 4)
            .first(909)
            .subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Exception {
                    System.out.println(integer);
                }
            });
    }
}
