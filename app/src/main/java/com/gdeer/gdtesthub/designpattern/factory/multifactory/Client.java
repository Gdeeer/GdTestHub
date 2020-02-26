package com.gdeer.gdtesthub.designpattern.factory.multifactory;

public class Client {
    public static void main(String[] args) {
        BaseFactory factory = new Factory1();
        BaseFactory factory2 = new Factory2();

        BaseProduct product = factory.createProduct();
        BaseProduct product2 = factory2.createProduct();

        product.method1();
        product.method2();
        product2.method1();
        product2.method2();
    }
}
