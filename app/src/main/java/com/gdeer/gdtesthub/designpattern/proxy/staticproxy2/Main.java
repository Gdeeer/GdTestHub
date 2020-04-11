package com.gdeer.gdtesthub.designpattern.proxy.staticproxy2;

public class Main {
    public static void main(String[] args) {
        Operate classB = new ClassB();

        classB.op1();
        classB.op2();
        classB.op3();
    }
}
