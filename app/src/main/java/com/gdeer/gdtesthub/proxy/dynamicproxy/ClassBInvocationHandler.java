package com.gdeer.gdtesthub.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ClassBInvocationHandler implements InvocationHandler {

    private Object target;

    public ClassBInvocationHandler() {
        target = new ClassA();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("classB " + method.getName());
        Object obj = method.invoke(target, args);
        return obj;
    }
}
