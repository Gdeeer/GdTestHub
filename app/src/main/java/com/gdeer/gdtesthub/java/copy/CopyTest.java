package com.gdeer.gdtesthub.java.copy;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyTest {
    public static void main(String[] args) {
        // new ArrayList<>(list1)，用了 Arrays.copyOf 方法，浅拷贝
        A a1 = new A();
        a1.name = "1";
        A a2 = new A();
        a2.name = "2";
        List<A> list1 = new ArrayList<>();
        list1.add(a1);
        list1.add(a2);
        List<A> list2 = new ArrayList<>(list1);
        System.out.println(list1);
        System.out.println(list2);

        // 用了数组类的 clone 方法，浅拷贝
        A[] arr1 = new A[]{a1, a2};
        A[] arr2 = arr1.clone();
        System.out.println(arr1 + " " + Arrays.toString(arr1));
        System.out.println(arr2 + " " + Arrays.toString(arr2));
    }

    static class A {
        String name;

        @NonNull
        @Override
        public String toString() {
            // com.gdeer.gdtesthub.java.copy.CopyTest$A;@2626b418
            // ---> A;@2626b418
            String superString = super.toString();
            int dollarIndex = superString.indexOf("$");
            return superString.substring(dollarIndex + 1);
        }
    }
}
