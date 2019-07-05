package com.gdeer.gdtesthub.viewModel.userList;

import com.gdeer.gdtesthub.viewModel.User;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class UserListViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (true) {
                    SystemClock.sleep(1000);
                    List<User> usersInternal = users.getValue();
                    if (usersInternal == null) {
                        usersInternal = new ArrayList<>();
                    }
                    usersInternal.add(0, new User("gd" + i++, "man"));
                    users.postValue(usersInternal);
                }
            }
        }).start();
    }
}