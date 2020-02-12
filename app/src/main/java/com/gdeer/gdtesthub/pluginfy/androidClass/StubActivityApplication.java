package com.gdeer.gdtesthub.pluginfy.androidClass;

import android.app.Application;
import android.content.Context;

import me.weishu.reflection.Reflection;

/**
 * 要使用的 Application，进行 hook
 */
public class StubActivityApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // 解除 android P 上的私有 api 限制，见 http://weishu.me/2018/06/07/free-reflection-above-android-p/
        Reflection.unseal(base);

        // hook
        AMSPHookHelper.hookAMSP();
        AMSPHookHelper.hookActivityThread();
    }
}
