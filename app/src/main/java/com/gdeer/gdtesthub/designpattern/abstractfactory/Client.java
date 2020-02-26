package com.gdeer.gdtesthub.designpattern.abstractfactory;

public class Client {
    public static void main(String[] args) {
        BaseFactory factory1 = new Factory1();
        BaseFactory factory2 = new Factory2();

        BaseProductA productA1 = factory1.createProductA();
        BaseProductB productB1 = factory1.createProductB();
        BaseProductA productA2 = factory2.createProductA();
        BaseProductB productB2 = factory2.createProductB();

        productA1.doSomething();
        productB1.doSomething();
        productA2.doSomething();
        productB2.doSomething();
    }
}
