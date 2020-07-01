package com.gdeer.gdtesthub.view.textview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.view.textview.baseuse.BaseUseFragment;
import com.gdeer.gdtesthub.view.textview.edit.EditFragment;

public class TextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, EditFragment.newInstance())
                    .commitNow();
        }
    }
}
