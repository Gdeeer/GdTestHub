package com.gdeer.gdtesthub.java;

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

enum Shape {
    Circle(1),
    Rectangle(4),
    Triangle(3);

    private int edgeCount;

    Shape(int count) {
        edgeCount = count;
    }

    public int getEdgeCount() {
        return edgeCount;
    }
}