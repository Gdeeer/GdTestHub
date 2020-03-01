package com.gdeer.gdtesthub.generic.typeparameter;

import com.gdeer.gdtesthub.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Formatter;

/**
 * 获取泛型对象的参数类型
 * <p>
 * 1、类中有 class 信息（类中有一个 Class 字段）
 * 2、父类中有 class 信息（父类是泛型类，并指定了参数类型）
 * 3、持有者中有 class 信息（是一个类的 Field、Method）
 */
public class GetParaType {
    public static void main(String[] args) {
        // 第一种情况
        A1<String> a1 = new A1<>(String.class);
        System.out.println(a1.mClass);

        // 第二种情况
        A2 a2 = new A2();
        printClass(a2);
        // 必须是匿名类吗？不是，而是必须提前声明 String
        A2Parent<String> parent = new A2Parent<String>() {
        };
        printClass(parent);

        // 第三种情况
        A3User a3User = new A3User();
        try {
            Field field = FieldUtils.getField(a3User.getClass(), "a3");
            System.out.println();
            System.out.println(((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printClass(Object obj) {
        System.out.println();
        format("obj: ", obj);
        format("class: ", obj.getClass());
        format("superClass: ", obj.getClass().getSuperclass());
        format("genericSuperClass: ", obj.getClass().getGenericSuperclass());
        format("genericSuperClass typeArgument: ", ((ParameterizedType) obj.getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    static void format(String key, Object value) {
        Formatter formatter = new Formatter(System.out);
        formatter.format("%-35s %-50s\n", key, value);
    }

    /**
     * 第一种情况
     */
    static class A1<T> {
        public Class<T> mClass;

        public A1(Class<T> aClass) {
            mClass = aClass;
        }
    }


    /**
     * 第二种情况
     */
    static class A2 extends A2Parent<String> {
    }

    static class A2Parent<T> {
    }


    /**
     * 第三种情况
     */
    static class A3<T> {
    }

    static class A3User {
        A3<String> a3 = new A3<>();
    }
}
