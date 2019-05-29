package com.gdeer.gdtesthub.classLoad;

import android.util.Log;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader myLoader = new MyClassLoader();

        System.out.println(String.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());

        System.out.println(ClassLoaderTest.class.getClassLoader());
        Object obj = myLoader.loadClass("com.gdeer.gdtesthub.classLoad.TTT").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj.getClass().getClassLoader().getParent());
        System.out.println(obj instanceof com.gdeer.gdtesthub.classLoad.TTT);

    }

    public void dosomething() {
        Log.d("zhangjl", ClassLoader.getSystemClassLoader() + "");
        Log.d("zhangjl", TTT.class.getClassLoader() + "");
        Log.d("zhangjl", TTT.class.getClassLoader().getParent() + "");
        Log.d("zhangjl", TTT.class.getClassLoader().getParent().getParent() + "");
        Log.d("zhangjl", TTT.class.getClassLoader().getParent().getParent().getParent() + "");
        Log.d("zhangjl", String.class.getClassLoader() + "");
    }

}
