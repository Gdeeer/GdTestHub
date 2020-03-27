package com.gdeer.gdtesthub.viewModel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.viewModel.userDetail.UserDetailFragment;

public class VmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vm_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, UserDetailFragment.newInstance())
                .commitNow();
        }
    }
}
