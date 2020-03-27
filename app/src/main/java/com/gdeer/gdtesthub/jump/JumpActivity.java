package com.gdeer.gdtesthub.jump;

import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.databinding.ActivityJumpBinding;

public class JumpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("zhangjl", "jump onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);

        ActivityJumpBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_jump);
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JumpActivity.this, TargetActivity.class);
                startActivity(intent);
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JumpActivity.this, TargetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        Log.d("zhangjl", "jump onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("zhangjl", "jump onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("zhangjl", "jump onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("zhangjl", "jump onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("zhangjl", "jump onDestroy");
        super.onDestroy();
    }
}
