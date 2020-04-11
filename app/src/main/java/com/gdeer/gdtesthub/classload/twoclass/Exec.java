package com.gdeer.gdtesthub.classload.twoclass;

/**
 * classloader load A 时，如果 A 引用了 B，B由谁来 load?
 *
 * classloader load A 时，不会加载
 */
public class Exec {
    public static void main(String[] args) {
        AClassLoader classLoader = new AClassLoader();
        try {
            Class c = classLoader.loadClass("com.gdeer.gdtesthub.classload.twoclass.Exec$A");
            A a = (A) c.newInstance();
            B b = a.b;
            System.out.println(a.getClass().getClassLoader());
            System.out.println(b.getClass().getClassLoader());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    static class AClassLoader extends ClassLoader {
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            return super.loadClass(name);
        }
    }

    static class A {
        static {
            System.out.println("A init");
        }
        B b = new B();
    }

    static class B {
        static {
            System.out.println("B init");
        }
    }
}
