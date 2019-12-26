package com.gdeer.gdtesthub.listview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.gdeer.gdtesthub.R;

import java.util.Arrays;
import java.util.List;

public class NewsFragment extends Fragment {
    private ListView mListView;
    private MyAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        mListView = view.findViewById(R.id.lv_frag);

        String s = "b b b b b b b b b b b b";
        String[] array = s.split(" ");
        List<String> list = Arrays.asList(array);
        mAdapter = new MyAdapter(list);
        mListView.setAdapter(mAdapter);
        mListView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1200));
        mListView.setBackgroundResource(R.color.colorAccent);

        return view;
    }
}