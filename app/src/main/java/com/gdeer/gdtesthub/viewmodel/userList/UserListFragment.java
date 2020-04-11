package com.gdeer.gdtesthub.viewmodel.userList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gdeer.gdtesthub.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserListFragment extends Fragment {

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @BindView(R.id.rcv_user)
    RecyclerView mRcvUser;
    Unbinder unbinder;

    private UserListViewModel mUserListViewModel;
    private UserListAdapter mUserListAdapter = new UserListAdapter();

    private UserListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_list_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mUserListViewModel = ViewModelProviders.of(this).get(UserListViewModel.class);

        mRcvUser.setLayoutManager(new LinearLayoutManager(getContext()));

        mRcvUser.setAdapter(mUserListAdapter);
        mUserListViewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            mUserListAdapter.setNewData(users);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
