package com.gdeer.gdtesthub.viewmodel.userList;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.viewmodel.User;

public class UserListAdapter extends BaseQuickAdapter<User, BaseViewHolder> {

    public UserListAdapter() {
        super(R.layout.layout_item_user_rcv);
    }

    @Override
    protected void convert(BaseViewHolder helper, User item) {
        helper.setText(R.id.tv_username, item.getName());
    }
}
