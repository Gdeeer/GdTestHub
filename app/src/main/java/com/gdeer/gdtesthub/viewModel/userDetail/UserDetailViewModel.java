package com.gdeer.gdtesthub.viewModel.userDetail;

import com.gdeer.gdtesthub.viewModel.User;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.SystemClock;

public class UserDetailViewModel extends ViewModel {
    private MutableLiveData<User> user;

    public MutableLiveData<User> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
            loadUser();
        }
        return user;
    }

    private void loadUser() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    SystemClock.sleep(1000);
                    int randomI = (int) (Math.random() * 10);
                    user.postValue(new User("gd " + randomI, "man"));
                }
            }
        }).start();
    }
}
