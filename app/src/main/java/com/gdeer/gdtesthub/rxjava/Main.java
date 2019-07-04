package com.gdeer.gdtesthub.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String[] ps = {"p1", "p2", "p3"};
//                for (String p : ps) {
//                    System.out.println("new Thread() Thread: " + Thread.currentThread());
//                    System.out.println(p);
//                }
//            }
//        }).start();
//


        Observable<String> observable = new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                System.out.println("subscribeActual Thread: " + Thread.currentThread());

                String[] ps = {"p1", "p2", "p3"};
                for (String p : ps) {
                    observer.onNext(p);
                }
                observer.onComplete();
            }
        };

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext Thread: " + Thread.currentThread());
                System.out.println(s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        System.out.println("subscribe Thread: " + Thread.currentThread());

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(observer);


        getObservableBitmap()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println(s);
                    }
                });

        Thread.sleep(2000);
    }

    public static Observable<String> getObservableBitmap() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) {
                System.out.println("getObservableBitmap " + Thread.currentThread());
                String s = "sss";
                if (s != null) {
                    e.onNext(s);
                } else {
                    e.onError(new NullPointerException());
                }
            }
        });
    }
}
