package com.gdeer.gdtesthub.annotation.usecase;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker {
    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method declaredMethod : cl.getDeclaredMethods()) {
            UseCase useCase = declaredMethod.getAnnotation(UseCase.class);
            if (useCase != null) {
                System.out.println(useCase.id() + " " + useCase.description());
                useCases.remove(Integer.valueOf(useCase.id()));
            }
        }

        for (Integer useCase : useCases) {
            System.out.println("missing " + useCase);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }
}
