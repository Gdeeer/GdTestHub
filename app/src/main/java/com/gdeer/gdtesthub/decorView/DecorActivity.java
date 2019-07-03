package com.gdeer.gdtesthub.decorView;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.databinding.ActivityDecorBinding;

public class DecorActivity extends Activity {

    private FakeNotiHelper mFakeNotiHelper;
    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ActivityDecorBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_decor);


        mView = new View(DecorActivity.this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, 200);
        int margin = 30;
        params.topMargin = margin;
        params.bottomMargin = margin;
        params.leftMargin = margin;
        params.rightMargin = margin;
        mView.setLayoutParams(params);
        mView.setBackgroundColor(Color.CYAN);

        mFakeNotiHelper = new FakeNotiHelper(this);
        mFakeNotiHelper.attach((ViewGroup) binding.getRoot());
        mFakeNotiHelper.setContentView(mView);

        binding.btnShow.setOnClickListener(v -> mFakeNotiHelper.show());

        binding.btnStop.setOnClickListener(v -> mFakeNotiHelper.stop());

        binding.btnHide.setOnClickListener(v -> mFakeNotiHelper.hide());
    }
}
