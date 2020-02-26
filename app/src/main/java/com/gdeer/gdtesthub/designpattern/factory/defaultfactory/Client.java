package com.gdeer.gdtesthub.designpattern.factory.defaultfactory;

public class Client {
    public static void main(String[] args) {
        BaseFactory factory = new Factory();
        BaseProduct product = factory.createProduct(Product1.class);
        product.method1();
        product.method2();

        BaseProduct product2 = factory.createProduct(Product2.class);
        product2.method1();
        product2.method2();
    }
}
