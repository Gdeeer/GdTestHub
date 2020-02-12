package com.gdeer.gdtesthub.pluginfy.hookTest;

import android.app.Application;
import android.content.Context;

public class AMSPHookApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

//        Reflection.unseal(base);
        AMSPHookHelper.init();
    }
}
