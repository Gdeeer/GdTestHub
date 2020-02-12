package com.gdeer.gdtesthub.pluginfy.androidClass;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;

import com.gdeer.gdtesthub.reflect.FieldUtils;

import java.lang.reflect.Proxy;

/**
 * 启动不在 androidManifest 中注册的 Activity 要 Hook 的东西
 *
 *
 * AMSP：即 AndroidManagerService 在用户 app 上的 Proxy
 * mCallback：ActivityThread 的 mH 对象中的 callback
 */
class AMSPHookHelper {
    private static final String TAG = "AMSPHook";

    static final String EXTRA_TARGET_INTENT = "extra_target_intent";


    static void hookAMSP() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            hookAMSPBefore26();
        } else {
            hookAMSPSince26();
        }
    }


    /**
     * android 26 以下版本 AMSP 的 hook
     */
    private static void hookAMSPBefore26() {
        try {
            Log.d(TAG, "hookAMSPBefore26");
            Class classActivityManagerNative = Class.forName("android.app.ActivityManagerNative");
            Object gDefault = FieldUtils.readStaticField(classActivityManagerNative, "gDefault");
            Log.d(TAG, "gDefault " + gDefault);
            Object mInstance = FieldUtils.readField(gDefault, "mInstance");
            Log.d(TAG, "mInstance " + mInstance);

            Class classIActivityManager = Class.forName("android.app.IActivityManager");
            Log.d(TAG, "classIActivityManager " + classIActivityManager);
            Object proxy = Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{classIActivityManager},
                new MockAMSP(mInstance)
            );

            FieldUtils.writeField(gDefault, "mInstance", proxy);
        } catch (Exception e) {
            Log.d(TAG, "hookAMSPBefore26 exception: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * android 26 及以上版本 AMSP 的 hook
     */
    private static void hookAMSPSince26() {
        try {
            Log.d(TAG, "hookAMSPSince26");
            Object IActivityManagerSingleton = FieldUtils.readStaticField(ActivityManager.class, "IActivityManagerSingleton");
            Log.d(TAG, "IActivityManagerSingleton " + IActivityManagerSingleton);
            Object mInstance = FieldUtils.readField(IActivityManagerSingleton, "mInstance");
            Log.d(TAG, "mInstance " + mInstance);

            Class classIActivityManager = Class.forName("android.app.IActivityManager");
            Log.d(TAG, "classIActivityManager " + classIActivityManager);
            Object proxy = Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                new Class[]{classIActivityManager},
                new MockAMSP(mInstance)
            );

            FieldUtils.writeField(IActivityManagerSingleton, "mInstance", proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void hookActivityThread() {
        try {
            Class classActivityThread = Class.forName("android.app.ActivityThread");
            Object currentActivityThread = FieldUtils.readStaticField(classActivityThread, "sCurrentActivityThread");
            Handler mH = (Handler) FieldUtils.readField(currentActivityThread, "mH");
            FieldUtils.writeField(mH, "mCallback", new MockHCallback(mH));
        } catch (Exception e) {
            Log.d(TAG, "hookActivityThread " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
