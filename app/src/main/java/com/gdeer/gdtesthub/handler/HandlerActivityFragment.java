package com.gdeer.gdtesthub.handler;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdeer.gdtesthub.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class HandlerActivityFragment extends Fragment {

    public HandlerActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_handler, container, false);
    }
}
