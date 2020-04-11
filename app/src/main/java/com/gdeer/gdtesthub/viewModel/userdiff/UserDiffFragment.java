package com.gdeer.gdtesthub.viewModel.userdiff;

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

public class UserDiffFragment extends Fragment {

    public static UserDiffFragment newInstance() {
        return new UserDiffFragment();
    }

    @BindView(R.id.rcv_user)
    RecyclerView mRcvUser;
    Unbinder unbinder;

    private UserDiffViewModel mUserDiffViewModel;
    private UserDiffAdapter mUserDiffAdapter = new UserDiffAdapter();

    private UserDiffFragment() {
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
        mUserDiffViewModel = ViewModelProviders.of(this).get(UserDiffViewModel.class);

        mRcvUser.setLayoutManager(new LinearLayoutManager(getContext()));

        mRcvUser.setAdapter(mUserDiffAdapter);
        mUserDiffViewModel.getUsers().observe(getViewLifecycleOwner(), users -> {
            mUserDiffAdapter.submitList(users);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
