package com.gdeer.gdtesthub.designpattern.factory.simplefactory;

public class Client {
    public static void main(String[] args) {
        BaseProduct product = SimpleFactory.createProduct(Product2.class);
        product.method1();
        product.method2();
    }
}
