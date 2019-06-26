package com.gdeer.gdtesthub.java;

import org.jetbrains.annotations.NotNull;

import android.support.annotation.NonNull;

public class NotNullTest {
    @NonNull
    private Integer id;
    @NotNull
    private String name;

    public NotNullTest(@NonNull Integer id, @NotNull String name) {
        this.id = id;
        this.name = name;
    }

    public void p(@NonNull String s) {
        System.out.println(s);
    }

    public void p1(@NotNull String s) {
        System.out.println(s);
    }

    public static void main(String[] args) {
        NotNullTest notNullTest = new NotNullTest(null, null);
        notNullTest.p(null);
    }
}
