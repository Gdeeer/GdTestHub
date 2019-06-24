package com.gdeer.gdtesthub.viewModel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.viewModel.userDetail.UserDetailFragment;
import com.gdeer.gdtesthub.viewModel.userList.UserListFragment;

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
