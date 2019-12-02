package com.gdeer.gdtesthub.systrace;

import android.app.Application;
import android.content.Context;
import android.os.Trace;
import android.util.Log;

public class TraceApplication extends Application {
    @Override
    public void onCreate() {
        Log.d("zhangjl", "application onCreate");
        Trace.beginSection("gdeer-Application");
        super.onCreate();
        Trace.endSection();
    }

    @Override
    protected void attachBaseContext(Context base) {
        Log.d("zhangjl", "attachBaseContext");
        super.attachBaseContext(base);
    }
}
