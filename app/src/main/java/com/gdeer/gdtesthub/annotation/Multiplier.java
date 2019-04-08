package com.gdeer.gdtesthub.annotation;

@ExtractInterface("IMultiplier")
public class Multiplier {
    public int multiply(int x, int y) {
        int tatal = 0;
        for (int i = 0; i < x; i++) {
            tatal = add(tatal, y);
        }
        return tatal;
    }

    private int add(int x, int y) {
        return x + y;
    }

    public static void main(String[] args) {
        Multiplier m = new Multiplier();
        System.out.println("11 * 16 = " + m.multiply(11, 16));
    }
}
