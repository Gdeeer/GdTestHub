package com.gdeer.gdtesthub.designpattern.abstractfactory;

public class Factory1 extends BaseFactory {
    @Override
    public BaseProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public BaseProductB createProductB() {
        return new ProductB1();
    }
}
