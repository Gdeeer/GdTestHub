package com.gdeer.gdtesthub.designpattern.factory.multifactory;

public class Factory1 extends BaseFactory {
    @Override
    BaseProduct createProduct() {
        return new Product1();
    }
}
