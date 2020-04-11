package com.gdeer.gdtesthub.java.enumtest;

import java.util.Arrays;

public class EnumDemo {
    public static void main(String[] args) {
        Shape s = Shape.Circle;
        System.out.println(Arrays.toString(Shape.values()));
        System.out.println(Shape.valueOf("Circle"));
        System.out.println(s.name());
        System.out.println(s.ordinal());
        System.out.println(s.getEdgeCount());
    }
}