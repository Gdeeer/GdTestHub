package com.gdeer.gdtesthub.viewModel.userList;

import com.gdeer.gdtesthub.viewModel.User;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;

public class UserDiffViewModel extends ViewModel {
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
                while (true) {
                    SystemClock.sleep(1000);
                    int randomI = (int) (Math.random() * 10);
                    List<User> usersTemp = new ArrayList<>();
                    for (int i = randomI; i < randomI + 3; i++) {
                        User userTemp = new User("gd " + i, "man");
                        usersTemp.add(userTemp);
                    }
                    users.postValue(usersTemp);
                }
            }
        }).start();
    }
}
