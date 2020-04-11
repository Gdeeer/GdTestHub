package com.gdeer.gdtesthub.designpattern.proxy.dynamicproxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        Operate classB = (Operate) Proxy.newProxyInstance(
            Operate.class.getClassLoader(),
            new Class[]{Operate.class},
            new ClassBInvocationHandler());

        System.out.println(classB.getClass());

        classB.op1();
        classB.op2();
        classB.op3();
    }
}
