package com.gdeer.gdtesthub.anr;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.d("zhangjl", "onCreate");
        super.onCreate();
        SystemClock.sleep(100000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("zhangjl", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
