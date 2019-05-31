package com.gdeer.gdtesthub.methodHandle;

import android.annotation.TargetApi;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

@TargetApi(26)
public class methodHandle {
    public static void main(String[] args) throws Throwable {
//        // public方法的Lookup
//        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
//        // 所有方法的Lookup
//        MethodHandles.Lookup lookup = MethodHandles.lookup();
//
//
//        // invoke使用
//        MethodType mt = MethodType.methodType(String.class, char.class, char.class);
//        MethodHandle replaceMH = publicLookup.findVirtual(String.class, "replace", mt);
//        String output = (String) replaceMH.invoke("jovo", 'o', 'a');
//        System.out.println(output);
//
//        // invokeWithArguments使用
//        MethodType mtList = MethodType.methodType(List.class, Object[].class);
//        MethodHandle asList = publicLookup.findStatic(Arrays.class, "asList", mtList);
//        List<Integer> list = (List<Integer>) asList.invokeWithArguments(1, 2);
//        System.out.println(list);
//
//        // invokeExact
//        MethodType mtNumber = MethodType.methodType(int.class, int.class, int.class);
//        MethodHandle sumMH = lookup.findStatic(Integer.class, "sum", mtNumber);
//        int sum = (int) sumMH.invokeExact(1, 11);
//        System.out.println(sum);
    }
}
