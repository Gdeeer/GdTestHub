package com.gdeer.gdtesthub.viewModel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.viewModel.userList.UserListFragment;

public class VmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vm_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, UserListFragment.newInstance())
                .commitNow();
        }
    }
}
