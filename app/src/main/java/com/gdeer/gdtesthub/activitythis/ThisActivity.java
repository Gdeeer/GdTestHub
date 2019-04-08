package com.gdeer.gdtesthub.activitythis;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.anr.AnrActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ThisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_this);

        A a = new A();
        a.test();

        TextView textView;
    }

    class A {
        void test() {
            Log.d("zhangjl", this.toString());
            Log.d("zhangjl", ThisActivity.this.toString());
        }
    }
}
