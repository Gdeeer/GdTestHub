package com.gdeer.gdtesthub.poplibrary.okhttp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TryWithResource {
    private void tryCatchFinally() {
        InputStream is = null;
        OutputStream os = null;
        try {
            //...
        } catch (Exception e) {
            //...
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e2) {
                //...
            }
        }
    }

    private void tryWithResource() {
        try (
            InputStream is = new FileInputStream("...");
            OutputStream os = new FileOutputStream("...");
        ) {
            //...
        } catch (IOException e) {
            //...
        }
    }
}
