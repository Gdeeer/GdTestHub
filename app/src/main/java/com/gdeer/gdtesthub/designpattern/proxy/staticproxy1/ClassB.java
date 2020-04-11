package com.gdeer.gdtesthub.designpattern.proxy.staticproxy1;

public class ClassB {
    private ClassA a;

    public ClassB() {
        a = new ClassA();
    }

    public void op1() {
        System.out.println("ClassB op1");
        a.op1();
    }

    public void op2() {
        System.out.println("ClassB op2");
        a.op2();
    }

    public void op3() {
        System.out.println("ClassB op3");
        a.op3();
    }
}