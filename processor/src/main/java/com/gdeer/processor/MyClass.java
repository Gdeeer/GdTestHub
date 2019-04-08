package com.gdeer.processor;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.lang.model.element.Modifier;

public class MyClass {
    private String mString;

    public static void main(String[] args) throws IOException {
        MethodSpec main = MethodSpec.methodBuilder("main")
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .returns(void.class)
            .addParameter(String[].class, "args")
            .addStatement("$T.out.println($S)", System.class, 234)
            .addStatement("$T.out.println($L)", System.class, 234)
            .build();

        MethodSpec calculate = MethodSpec.methodBuilder("calculate")
            .returns(int.class)
            .addCode(""
                + "int total = 0;\n"
                + "for (int i = 0; i < 10; i++) {\n"
                + "  total += i;\n"
                + "}\n"
                + "return total;\n")
            .build();

        MethodSpec calculate1 = MethodSpec.methodBuilder("calculate1")
            .returns(int.class)
            .addStatement("int total = 0")
            .beginControlFlow("for (int i = 0; i < 10; i++)")
            .addStatement("total += i")
            .endControlFlow()
            .build();

        MethodSpec methodSpec = computeRange("multiply10to20", 10, 20, "*");

        MethodSpec printAInternal = MethodSpec.methodBuilder("printAInternal")
            .addStatement("$T.out.println('a')", System.class)
            .build();

        MethodSpec printA = MethodSpec.methodBuilder("byteToHex")
            .addStatement("$N()", printAInternal)
            .build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
            .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
            .addMethod(main)
            .addMethod(calculate)
            .addMethod(calculate1)
            .addMethod(methodSpec)
            .addMethod(printAInternal)
            .addMethod(printA)
            .build();

        JavaFile javaFile = JavaFile.builder("com.gdeer.lib", helloWorld).build();

        javaFile.writeTo(System.out);
    }

    private static MethodSpec computeRange(String name, int from, int to, String op) {
        return MethodSpec.methodBuilder(name)
            .returns(int.class)
            .addStatement("int result = S")
            .build();
    }
}
