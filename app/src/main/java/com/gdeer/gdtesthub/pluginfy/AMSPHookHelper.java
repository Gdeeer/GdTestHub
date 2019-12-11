package com.gdeer.gdtesthub.pluginfy;

import android.app.ActivityManager;
import android.util.Log;

import com.gdeer.gdtesthub.reflect.FieldUtils;

import java.lang.reflect.Proxy;

public class AMSPHookHelper {
    public static void init() {
        try {
            Object IActivityManagerSingleton = FieldUtils.readStaticField(ActivityManager.class, "IActivityManagerSingleton");
            Log.d("AMSHook", "IActivityManagerSingleton " + IActivityManagerSingleton);
            Object mInstance = FieldUtils.readField(IActivityManagerSingleton, "mInstance");
            Log.d("AMSHook", "mInstance " + mInstance);

            Class classIActivityManager = Class.forName("android.app.IActivityManager");
            Log.d("AMSHook", "classIActivityManager " + classIActivityManager);
            Object proxy = Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{ classIActivityManager },
                new SelfAMSP(mInstance)
            );

            FieldUtils.writeField(IActivityManagerSingleton, "mInstance", proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
