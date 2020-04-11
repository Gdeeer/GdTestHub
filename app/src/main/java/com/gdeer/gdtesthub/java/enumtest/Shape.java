package com.gdeer.gdtesthub.java.enumtest;

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
