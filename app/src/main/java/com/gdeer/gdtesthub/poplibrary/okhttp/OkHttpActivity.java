package com.gdeer.gdtesthub.poplibrary.okhttp;

import com.gdeer.gdtesthub.R;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        Button button = findViewById(R.id.btn_fetch);
        TextView tv = findViewById(R.id.tv_ok_http);

        button.setOnClickListener(v -> {
            String url = "http://restapi.amap.com/v3/ip?key=35f7714105623a1521c223da251161b8&output=JSON";
            new Thread(() -> {
                try {
                    String s = run(url);
                    runOnUiThread(() -> tv.setText(s));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    String run(String url) throws IOException {
        // 缓存文件，getExternalCacheDir()：/storage/emulated/0/Android/data/packagename/cache
        File file = new File(getExternalCacheDir(), "gd_okhttp_cache");
        int cacheSize = 10 * 1024 * 1024;
        OkHttpClient client = new OkHttpClient.Builder()
            // 添加缓存文件和缓存大小
            .cache(new Cache(file, cacheSize))
            // 添加拦截器，给 response 加上 "Cache-Control" 的 header
            .addNetworkInterceptor(new NetCacheInterceptor())
            .build();

        // CacheControl 会给 request 加上的 "Cache-Control" 的 header
        CacheControl cacheControl = new CacheControl.Builder()
            .maxAge(10, TimeUnit.SECONDS)
            .build();

        Request request = new Request.Builder()
            .url(url)
            // 添加 cacheControl
            .cacheControl(cacheControl)
            .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
