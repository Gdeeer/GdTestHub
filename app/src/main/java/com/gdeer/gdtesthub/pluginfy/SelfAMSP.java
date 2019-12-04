package com.gdeer.gdtesthub.pluginfy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SelfAMSP implements InvocationHandler {

    private Object mOriginAMP;

    public SelfAMSP(Object originAMP) {
        mOriginAMP = originAMP;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d("AMSHook", "invoke() called with: proxy = [" + proxy + "], method = [" + method + "], args = [" + args + "]");
        return method.invoke(mOriginAMP, args);
    }
}
