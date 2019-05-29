package com.gdeer.gdtesthub.classLoad;

public class MyCL2 extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
