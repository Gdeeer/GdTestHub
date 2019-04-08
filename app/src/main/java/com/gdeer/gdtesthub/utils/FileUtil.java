package com.gdeer.gdtesthub.utils;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {
    /**
     * 从asset目录下复制文件到目标路径
     *
     * @param context       上下文用于读取asset目录文件
     * @param assetFilePath asset文件路径
     * @param targetFile    复制目标文件
     * @param cover         是否覆盖已存在目标文件
     */
    public static void copyFileFromAssets(Context context, String assetFilePath, File targetFile, boolean cover) {
        if (targetFile.exists()) {
            if (cover) {
                targetFile.delete();
            } else {
                return;
            }
        }

        InputStream in = null;
        FileOutputStream out = null;
        try {
            byte[] buffer = new byte[5 * 1024];   //一次取出的字节数大小,缓冲区大小
            int numberRead;
            in = context.getAssets().open(assetFilePath);
            out = new FileOutputStream(targetFile);
            while ((numberRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, numberRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.closeSilently(in, out);
        }
    }
}
