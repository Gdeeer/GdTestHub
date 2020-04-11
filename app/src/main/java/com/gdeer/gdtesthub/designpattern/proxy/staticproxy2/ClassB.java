package com.gdeer.gdtesthub.designpattern.proxy.staticproxy2;

class ClassB implements Operate {
    private ClassA a;

    public ClassB() {
        a = new ClassA();
    }

    @Override
    public void op1() {
        System.out.println("ClassB op1");
        a.op1();
    }

    @Override
    public void op2() {
        System.out.println("ClassB op2");
        a.op2();
    }

    @Override
    public void op3() {
        System.out.println("ClassB op3");
        a.op3();
    }
}
