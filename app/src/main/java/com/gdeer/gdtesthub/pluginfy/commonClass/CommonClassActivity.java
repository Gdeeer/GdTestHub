package com.gdeer.gdtesthub.pluginfy.commonClass;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.utils.FileUtil;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * ClassLoader 加载外部 apk 内的普通类
 * 无需 hook
 */
public class CommonClassActivity extends AppCompatActivity {

    private static String PLUGIN_APK_NAME = "simple.apk";
    private DexClassLoader mClassLoader;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        // 将 simple.apk 复制到 /data/data/packageName/files
        File apkFile = newBase.getFileStreamPath(PLUGIN_APK_NAME);
        FileUtil.copyFileFromAssets(newBase, PLUGIN_APK_NAME, apkFile, false);

        // 获取 dex 文件（即 simple.apk）的路径
        String dexPath = apkFile.getAbsolutePath();
        // 构建 classLoader，optDir 设为 dexPath
        mClassLoader = new DexClassLoader(dexPath, dexPath, null, getClassLoader());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_class);
        Button btnUse = findViewById(R.id.btn_use_common_class);

        btnUse.setOnClickListener(v -> {
            try {
                // 点击后，加载 Person 类，调用 set、get 方法
                Class<?> personClass = mClassLoader.loadClass("com.gdeer.simpleapk.Person");
                Object person = personClass.newInstance();
                Method setName = personClass.getMethod("setName", String.class);
                Method getName = personClass.getMethod("getName");
                setName.invoke(person, "gdeer");
                String name = (String) getName.invoke(person);
                Toast.makeText(CommonClassActivity.this, name, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
