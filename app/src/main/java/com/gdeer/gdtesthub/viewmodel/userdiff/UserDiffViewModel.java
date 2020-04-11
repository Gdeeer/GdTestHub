package com.gdeer.gdtesthub.viewmodel.userdiff;

import com.gdeer.gdtesthub.viewmodel.User;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
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
