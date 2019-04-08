package com.gdeer.gdtesthub.java.getName;

public class GetNameTest {
    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3};
        float[] floats = new float[]{1, 2, 3};
        String[] strings = new String[]{"a", "b", "c"};
        Integer[] integers = new Integer[]{1, 2, 3};
        System.out.println(ints);
        System.out.println(floats);
        System.out.println(strings);

        GetNameTest getNameTest = new GetNameTest();
        System.out.println(getNameTest);
        System.out.println(getNameTest.getClass());
        System.out.println(getNameTest.getClass().getName());
        System.out.println(strings.getClass());
        System.out.println(strings.getClass().getName());
    }
}
