package com.gdeer.gdtesthub.designpattern.factory.defaultfactory;

public class Factory extends BaseFactory {
    @Override
    public <T extends BaseProduct> T createProduct(Class<T> c) {
        try {
            return c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
