package com.gdeer.gdtesthub.designpattern.proxy.dynamicproxy;

public class ClassA implements Operate {
    @Override
    public void op1() {
        System.out.println("ClassA op1");
    }

    @Override
    public void op2() {
        System.out.println("ClassA op2");
    }

    @Override
    public void op3() {
        System.out.println("ClassA op3");
    }
}
