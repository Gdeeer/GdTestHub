package com.gdeer.gdtesthub;

import com.gdeer.annotation.BindView;
import com.gdeer.gdtesthub.annotation.usecase.Gdeer2;
import com.gdeer.gdtesthub.anr.AnrActivity;
import com.gdeer.gdtesthub.dayDream.MyDaydreamService;
import com.gdeer.gdtesthub.db.DbActivity;
import com.gdeer.gdtesthub.finish.FinishActivity;
import com.gdeer.gdtesthub.handler.HandlerActivity;
import com.gdeer.gdtesthub.location.LocationActivity;
import com.gdeer.gdtesthub.retrofit.RetrofitActivity;
import com.gdeer.gdtesthub.textView.TextActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @BindView(1)
    private TextView mTextView1;

    @BindView(2)
    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
//        classLoaderTest.dosomething();

        String s = new String();
        s = "aaa";
        Log.d("zhangjl", s.toString());
    }

    @Gdeer2
    public void finishTestOnClick(View view) {
        Intent intent = new Intent(this, FinishActivity.class);
        startActivity(intent);
    }

    public void ANRTestOnClick(View view) {
        Intent intent = new Intent(this, AnrActivity.class);
        startActivity(intent);
    }

    public void startDayDreamOnClick(View view) {
        Intent intent = new Intent(this, MyDaydreamService.class);
        startService(intent);
    }

    public void startLocation(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void startDB(View view) {
        Intent intent = new Intent(this, DbActivity.class);
        startActivity(intent);
    }

    public void startRetrofit(View view) {
        Intent intent = new Intent(this, RetrofitActivity.class);
        startActivity(intent);
    }

    public void startHandler(View view) {
        Intent intent = new Intent(this, HandlerActivity.class);
        startActivity(intent);
    }

    public void startText(View view) {
        Intent intent = new Intent(this, TextActivity.class);
        startActivity(intent);
    }
}
