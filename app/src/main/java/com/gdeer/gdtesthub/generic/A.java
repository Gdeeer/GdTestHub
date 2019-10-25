package com.gdeer.gdtesthub.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class A<T> {
    public void main(String[] args) {
        List<String> listStr = new ArrayList<>();
        listStr.add("a");
        List<Integer> listInt = new ArrayList<>();
        listInt.add(1);
//        printCollection1(listStr);
//        printCollection1(listInt);
        printCollection2(listStr);
        printCollection2(listInt);

        List<?> listSome = new ArrayList<String>();
        listSome = new ArrayList<Integer>();
        listSome.size();

        List<T> listSome2 = new ArrayList<>();
        // 报错
        // listSome2 = new ArrayList<Integer>();

        // 报错
        // List<Object> listObject = new ArrayList<String>();
    }

    // 类型擦除
    // 把 List<Integer> 转为 Collection<T> 后，可以添加任意类型的对象
    private <T> void printCollection1(Collection<T> collection) {
        collection.add((T) "testItem");
        System.out.println(collection);
    }

    private static void printCollection2(Collection<?> collection) {
        // 报错
        // collection.add((?) "testItem");
        System.out.println(collection);
    }

    // 报错
    // private static <T> void printCollection3(Collection<T extends B> collection) {
    // }

    private static void printCollection4(Collection<? extends B> collection) {
        System.out.println(collection);
    }

    public <E> boolean addAll(Collection<?> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        return numNew != 0;
    }
}

class B {

}
