package com.gdeer.gdtesthub.listview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;

import com.gdeer.gdtesthub.R;

import java.util.Arrays;
import java.util.List;

public class MyListActivity extends AppCompatActivity {

    private MyListView mListView;
    private MyAdapter mAdapter;
    private NewsLayout mNewsLayout;
    private NewsFragment mNewsFragment;
    private boolean mIsInReadState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListView = findViewById(R.id.listV);

        String s = "a a a a a a a a a a a a";
        String[] array = s.split(" ");
        List<String> list = Arrays.asList(array);
//        List<String> list = new ArrayList<>();
        mAdapter = new MyAdapter(list);
        mListView.setAdapter(mAdapter);
        mListView.setTagName("main");
        // 设置不拦截
//        mListView.setNeedSuperIntercept(false);

        mNewsLayout = (NewsLayout) LayoutInflater.from(MyListActivity.this).inflate(R.layout.layout_news, null);
        mListView.addFooterView(mNewsLayout);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                View firstVisibleView = view.getChildAt(0);

                if (firstVisibleItem == totalItemCount - 1) {
                    if (!mIsInReadState) {
                        mIsInReadState = true;
                        Log.d("zhangjl", "mIsInReadState = true");
                    }
                } else {
                    if (mIsInReadState) {
                        mIsInReadState = false;
                        Log.d("zhangjl", "mIsInReadState = false");
                    }
                }
            }
        });
        mListView.setSwipeListener(new MyListView.SwipeListener() {
            @Override
            public void onSwipeUp() {
                Log.d("zhangjl", "swipeUp readState: " + mIsInReadState + " contentReachTop: " + mNewsFragment.isReachTop()
                    + " needSuper: " + mListView.isNeedSuperIntercept());
                if (mIsInReadState && mNewsFragment.isReachTop() && mListView.isNeedSuperIntercept()) {
                    mListView.setNeedSuperIntercept(false);
                }
            }

            @Override
            public void onSwipeDown() {
                Log.d("zhangjl", "swipeDown readState: " + mIsInReadState + " contentReachTop: " + mNewsFragment.isReachTop()
                    + " needSuper: " + mListView.isNeedSuperIntercept());
                if (mIsInReadState && mNewsFragment.isReachTop() && !mListView.isNeedSuperIntercept()) {
                    mListView.setNeedSuperIntercept(true);
                }
            }
        });

        new Handler().post(() -> {
            mNewsLayout.setHeight(mListView.getHeight());
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
        });
    }
}
