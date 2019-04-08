package com.gdeer.gdtesthub.annotation.usecase;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@interface MyCustom {
    int value();

    int val2();
}

class MyClass {

}