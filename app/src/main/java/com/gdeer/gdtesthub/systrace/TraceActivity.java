package com.gdeer.gdtesthub.systrace;

import android.os.Trace;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.gdeer.gdtesthub.R;

public class TraceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Trace.beginSection("gdeeeer");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace);
        Trace.endSection();
    }
}
