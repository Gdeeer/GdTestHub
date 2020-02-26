package com.gdeer.gdtesthub.designpattern.factory.multifactory;

public class Factory2 extends BaseFactory {
    @Override
    BaseProduct createProduct() {
        return new Product2();
    }
}