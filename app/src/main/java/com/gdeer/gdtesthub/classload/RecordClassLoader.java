package com.gdeer.gdtesthub.classload;

import android.util.Log;

import dalvik.system.PathClassLoader;

public class RecordClassLoader extends PathClassLoader {
    private static final String TAG = "gd_" + RecordClassLoader.class.getSimpleName();
    public RecordClassLoader(String dexPath, ClassLoader parent) {
        super(dexPath, parent);
    }

    public RecordClassLoader(String dexPath, String librarySearchPath, ClassLoader parent) {
        super(dexPath, librarySearchPath, parent);
    }


    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Log.d(TAG, "loadClass() called with: name = [" + name + "], resolve = [" + resolve + "]");
        return super.loadClass(name, resolve);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Log.d(TAG, "findClass() called with: name = [" + name + "]");
        return super.findClass(name);
    }
}
