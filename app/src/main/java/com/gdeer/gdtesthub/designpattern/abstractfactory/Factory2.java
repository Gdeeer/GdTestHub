package com.gdeer.gdtesthub.designpattern.abstractfactory;

public class Factory2 extends BaseFactory {
    @Override
    public BaseProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public BaseProductB createProductB() {
        return new ProductB2();
    }
}
