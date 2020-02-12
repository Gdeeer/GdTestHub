package com.gdeer.gdtesthub.pluginfy.androidClass;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * hook AMSP
 * 使其认为要启动的不是 targetActivity，而是 stubActivity
 */
public class MockAMSP implements InvocationHandler {

    private static final String TAG = "MockAMSP";
    private static final String START_ACTIVITY = "startActivity";

    private Object mBase;

    MockAMSP(Object base) {
        mBase = base;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d(TAG, "invoke: " + method.getName());
        if (START_ACTIVITY.equals(method.getName())) {
            // 找到旧的 intent
            Intent raw;
            int index = 0;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent) {
                    index = i;
                    break;
                }
            }
            raw = (Intent) args[index];

            // 创建新的 intent
            Intent newIntent = new Intent();
            String stubPackage = "com.gdeer.gdtesthub";
            ComponentName componentName = new ComponentName(stubPackage, StubActivity.class.getName());
            newIntent.setComponent(componentName);
            newIntent.putExtra(AMSPHookHelper.EXTRA_TARGET_INTENT, raw);

            // 替换旧的 intent 为新的 intent
            args[index] = newIntent;

            // 调用 "startActivity" 方法
            Log.d(TAG, "hook success");
            return method.invoke(mBase, args);
        }
        return method.invoke(mBase, args);
    }
}
