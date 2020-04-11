package com.gdeer.gdtesthub.viewmodel.userdiff;

import com.gdeer.gdtesthub.viewmodel.User;

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
