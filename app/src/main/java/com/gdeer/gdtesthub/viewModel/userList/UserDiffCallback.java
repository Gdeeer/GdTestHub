package com.gdeer.gdtesthub.viewModel.userList;

import com.gdeer.gdtesthub.viewModel.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

class UserDiffCallback extends DiffUtil.ItemCallback<User> {
    @Override
    public boolean areItemsTheSame(@NonNull User user, @NonNull User t1) {
        return user.getName().equals(t1.getName());
    }

    @Override
    public boolean areContentsTheSame(@NonNull User user, @NonNull User t1) {
        return user == t1;
    }
}
