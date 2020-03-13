package com.gdeer.gdtesthub.broadcast;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * 监听网络状态变化的广播是<粘性广播>，每次 register 都会直接收到一个广播
 */
public class RegisterApplication extends Application {

    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        // 每隔 3s，register 一个 Receiver，共 register 5 个
        Observable.intervalRange(0, 5, 0, 3, TimeUnit.SECONDS)
            .subscribe(aLong -> {
                MyConnectReceiver receiver = new MyConnectReceiver();
                IntentFilter intentFilter = new IntentFilter();
                // 监听网络状态变化的广播，每次 register 都会直接收到一个广播
                intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                registerReceiver(receiver, intentFilter);
            });
    }
}
