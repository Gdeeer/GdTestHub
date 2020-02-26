package com.gdeer.gdtesthub.designpattern.factory.defaultfactory;

abstract class BaseFactory {
    abstract <T extends BaseProduct> T createProduct(Class<T> c);
}
