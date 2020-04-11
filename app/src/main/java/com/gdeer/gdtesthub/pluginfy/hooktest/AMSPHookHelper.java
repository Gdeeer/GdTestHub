package com.gdeer.gdtesthub.pluginfy.hooktest;

import android.app.ActivityManager;
import android.util.Log;

import com.gdeer.gdtesthub.reflect.FieldUtils;

import java.lang.reflect.Proxy;

/**
 * hook 功能的测试
 */
public class AMSPHookHelper {
    private static final String TAG = "AMSPHookHelper";

    public static void init() {
        try {
            Object IActivityManagerSingleton = FieldUtils.readStaticField(ActivityManager.class, "IActivityManagerSingleton");
            Log.d(TAG, "IActivityManagerSingleton " + IActivityManagerSingleton);
            Object mInstance = FieldUtils.readField(IActivityManagerSingleton, "mInstance");
            Log.d(TAG, "mInstance " + mInstance);

            Class classIActivityManager = Class.forName("android.app.IActivityManager");
            Log.d(TAG, "classIActivityManager " + classIActivityManager);
            Object proxy = Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{classIActivityManager},
                new SelfAMSP(mInstance)
            );

            FieldUtils.writeField(IActivityManagerSingleton, "mInstance", proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
