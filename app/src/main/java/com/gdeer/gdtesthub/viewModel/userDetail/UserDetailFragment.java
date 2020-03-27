package com.gdeer.gdtesthub.viewModel.userDetail;

import com.gdeer.gdtesthub.R;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserDetailFragment extends Fragment {

    @BindView(R.id.tv_user_detail)
    TextView tvUserDetail;
    Unbinder unbinder;
    private UserDetailViewModel mViewModel;

    public static UserDetailFragment newInstance() {
        return new UserDetailFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_detail_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(UserDetailViewModel.class);

        mViewModel.getUser().observe(this, user -> {
            tvUserDetail.setText(user.getName() + " " + user.getGender());
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
