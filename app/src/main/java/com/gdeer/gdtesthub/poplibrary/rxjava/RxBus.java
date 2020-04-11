package com.gdeer.gdtesthub.poplibrary.rxjava;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * A simple event bus built with RxJava, used for subscribing to and publishing to subjects.
 * Allowing you to send data between activities, fragments, etc.
 *
 * Created by Ralken Liao
 */
public class RxBus {
    private static ConcurrentHashMap<Object, List<CompositeDisposable>> sSubscriptionsMap = new ConcurrentHashMap<>();

    private static RxBus sInstance = null;

    /**
     * Allow to access this component through single instance only
     */
    public static RxBus getInstance() {
        if (sInstance == null) {
            sInstance = new RxBus();
        }
        return sInstance;
    }

    private final PublishSubject<Object> mBusSubject;

    private RxBus() {
        mBusSubject = PublishSubject.create();
    }

    /**
     * Posts an object (usually an Event) to the bus
     */
    public void post(Object event) {
        mBusSubject.onNext(event);
    }

    /**
     * Subscribe to the specified subject and listen for updates on that subject. Pass in an object to associate
     * your registration with, so that you can unsubscribe later.
     * <br/><br/>
     * <b>Note:</b> Make sure to call {@link RxBus#unregister(Object)} to avoid memory leaks.
     */
    public <T> void subscribe(@NonNull Object lifecycle, final Class<T> eventClass, @NonNull Consumer<T> action) {
        Disposable disposable = mBusSubject
            .ofType(eventClass)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(action);
        CompositeDisposable compositeDisposable = new CompositeDisposable(disposable);

        List<CompositeDisposable> disposables = sSubscriptionsMap.get(lifecycle);
        if (disposables == null) {
            disposables = new ArrayList<CompositeDisposable>();
        }
        disposables.add(compositeDisposable);
        sSubscriptionsMap.put(lifecycle, disposables);
    }

    /**
     * Unregisters this object from the bus, removing all subscriptions.
     * This should be called when the object is going to go out of memory.
     */
    public static void unregister(@NonNull Object lifecycle) {
        //We have to remove the composition from the map, because once you dispose it can't be used anymore
        final List<CompositeDisposable> disposables = sSubscriptionsMap.remove(lifecycle);
        if (disposables != null && disposables.size() > 0) {
            for (CompositeDisposable compositeDisposable : disposables) {
                if (compositeDisposable != null) {
                    compositeDisposable.dispose();
                }
            }
        }
    }

    /**
     * Observable that will emmit everything posted to the event bus.
     */
    public Observable<Object> observable() {
        return mBusSubject;
    }

}