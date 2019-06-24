package com.gdeer.gdtesthub.viewModel.userList;

import com.gdeer.gdtesthub.R;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserListFragment extends Fragment {

    @BindView(R.id.rcv_user)
    RecyclerView mRcvUser;
    Unbinder unbinder;

    private UserListViewModel mUserListViewModel;
    private UserAdapter mUserAdapter = new UserAdapter();

    private UserDiffViewModel mUserDiffViewModel;
    private UserDiffAdapter mUserDiffAdapter = new UserDiffAdapter();

    public static UserListFragment newInstance() {
        return new UserListFragment();
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
        mUserDiffViewModel = ViewModelProviders.of(this).get(UserDiffViewModel.class);

        mRcvUser.setLayoutManager(new LinearLayoutManager(getContext()));

//        mRcvUser.setAdapter(mUserAdapter);
//        mUserListViewModel.getUsers().observe(this, users -> {
//            mUserAdapter.setNewData(users);
//        });

        mRcvUser.setAdapter(mUserDiffAdapter);
        mUserDiffViewModel.getUsers().observe(this, users -> {
            mUserDiffAdapter.submitList(users);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
