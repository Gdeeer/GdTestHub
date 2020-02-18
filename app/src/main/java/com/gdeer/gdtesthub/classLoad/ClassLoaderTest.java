package com.gdeer.gdtesthub.classLoad;

import android.util.Log;

import com.gdeer.gdtesthub.reflect.FieldUtils;

import java.util.Formatter;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader myLoader = new MyClassLoader();

        format("String.class.getClassLoader(): ", String.class.getClassLoader());
        format("ClassLoader.getSystemClassLoader(): ", ClassLoader.getSystemClassLoader());
        format("ClassLoaderTest.class.getClassLoader(): ", ClassLoaderTest.class.getClassLoader());

        System.out.println();
        Object ttt = myLoader.loadClass("com.gdeer.gdtesthub.classLoad.TTT").newInstance();
        format("ttt.getClass(): ", ttt.getClass());
        format("ttt.getClass().getClassLoader(): ", ttt.getClass().getClassLoader());
        format("ttt.getClass().getClassLoader().getParent(): ", ttt.getClass().getClassLoader().getParent());
        format("ttt instanceof com.gdeer.gdtesthub.classLoad.TTT: ", (ttt instanceof com.gdeer.gdtesthub.classLoad.TTT));

        try {
            System.out.println();
            Object bbb = FieldUtils.readField(ttt, "bbb");
            format("bbb.getClass(): ", bbb.getClass());
            format("bbb.getClass().getClassLoader(): ", bbb.getClass().getClassLoader());
            format("bbb.getClass().getClassLoader().getParent(): ", bbb.getClass().getClassLoader().getParent());
            format("bbb instanceof com.gdeer.gdtesthub.classLoad.TTT.BBB: ", (bbb instanceof com.gdeer.gdtesthub.classLoad.TTT.BBB));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void format(String key, Object value) {
        Formatter formatter = new Formatter(System.out);
        formatter.format("%-55s %-50s\n", key, value);
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
