package com.gdeer.gdtesthub.pluginfy.hookTest;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义 AMSP
 * 通过 InvocationHandler，动态代理技术
 */
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
