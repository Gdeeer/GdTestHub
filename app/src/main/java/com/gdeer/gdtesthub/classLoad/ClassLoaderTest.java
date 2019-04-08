package com.gdeer.gdtesthub.classLoad;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new ClassNotFoundException();
                }
            }
        };

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
