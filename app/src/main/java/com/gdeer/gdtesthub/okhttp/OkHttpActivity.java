package com.gdeer.gdtesthub.okhttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.gdeer.gdtesthub.R;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        TextView tv = findViewById(R.id.tv_ok_http);
        String url = "http://restapi.amap.com/v3/ip?key=35f7714105623a1521c223da251161b8&output=JSON";
        new Thread(() -> {
            try {
                String s = run(url);
                runOnUiThread(() -> tv.setText(s));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    String run(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(url)
            .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
