package com.gdeer.gdtesthub.systrace;

import android.app.Application;
import android.os.Trace;

public class TraceApplication extends Application {
    @Override
    public void onCreate() {
        Trace.beginSection("gdeer-Application");
        super.onCreate();
        Trace.endSection();
    }
}
