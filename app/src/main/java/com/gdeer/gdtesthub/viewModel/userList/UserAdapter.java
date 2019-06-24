package com.gdeer.gdtesthub.viewModel.userList;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.viewModel.User;

public class UserAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public UserAdapter() {
        super(R.layout.layout_item_user_rcv);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        helper.setText(R.id.tv_username, item.getName());
    }
}
