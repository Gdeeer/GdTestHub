package com.gdeer.gdtesthub.designpattern.factory.simplefactory;

class SimpleFactory {
    static <T extends BaseProduct> T createProduct(Class<T> c) {
        try {
            return c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
