package com.gdeer.gdtesthub.listview;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ListView;

import com.gdeer.gdtesthub.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView mListView;
    private MyAdapter mAdapter;
    private NewsLayout mNewsLayout;
    private NewsFragment mNewsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListView = findViewById(R.id.listV);

        String s = "a a a a a a a a a a a a";
        String[] array = s.split(" ");
//        List<String> list = Arrays.asList(array);
        List<String> list = new ArrayList<>();
        mAdapter = new MyAdapter(list);
        mListView.setAdapter(mAdapter);

        mNewsLayout = (NewsLayout) LayoutInflater.from(ListActivity.this).inflate(R.layout.layout_news, null);
        mListView.addFooterView(mNewsLayout);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (mNewsFragment == null || fragmentManager != null) {
            mNewsFragment = new NewsFragment();
            try {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.news_layout, mNewsFragment);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
