package com.gdeer.gdtesthub.viewModel.userdiff;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.viewModel.User;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class UserDiffAdapter extends ListAdapter<User, RecyclerView.ViewHolder> {
    protected UserDiffAdapter() {
        super(new UserDiffCallback());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_user_rcv, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        UserViewHolder userViewHolder = (UserViewHolder) viewHolder;
        User user = getItem(i);
        userViewHolder.tvUsername.setText(user.getName());
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        TextView tvUsername;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername = itemView.findViewById(R.id.tv_username);
        }
    }
}

