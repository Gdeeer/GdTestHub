package com.gdeer.gdtesthub.classload;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.gdeer.gdtesthub.reflect.FieldUtils;
import com.gdeer.gdtesthub.reflect.ReflectIllegalArgumentsException;

public class RecordApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        ClassLoader mNowClassLoader = base.getClassLoader();
        RecordClassLoader recordClassLoader = new RecordClassLoader(base.getPackageCodePath(), mNowClassLoader);
        Thread.currentThread().setContextClassLoader(recordClassLoader);

        try {
            Object packageInfo = FieldUtils.readField(base, "mPackageInfo");
            Log.d("zhangjl", packageInfo.toString());
            FieldUtils.writeField(packageInfo, "mClassLoader", recordClassLoader);
            Log.d("zhangjl", "write");
        } catch (IllegalAccessException | ReflectIllegalArgumentsException e) {
            e.printStackTrace();
        }

        super.attachBaseContext(base);
    }
}
