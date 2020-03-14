package com.gdeer.gdtesthub.java.JavaPTest;

import android.util.Log;


/**
 *  0: ldc           #2      // String hello
 *  2: new           #3      // class java/lang/StringBuilder
 *  5: dup
 *  6: invokespecial #4      // Method java/lang/StringBuilder."<init>":()V
 *  9: ldc           #5      // String there is
 * 11: invokevirtual #6      // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * 14: aload_0
 * 15: invokevirtual #7      // Method java/lang/Object.getClass:()Ljava/lang/Class;
 * 18: invokevirtual #8      // Method java/lang/Class.getSimpleName:()Ljava/lang/String;
 * 21: invokevirtual #6      // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * 24: ldc           #9      // String  end
 * 26: invokevirtual #6      // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
 * 29: invokevirtual #10     // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
 * 32: invokestatic  #11     // Method android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
 * 35: pop
 * 36: return
 */
public class A {
    private void method() {
        Log.d("hello", "there is " + getClass().getSimpleName() + " end");
    }
}
