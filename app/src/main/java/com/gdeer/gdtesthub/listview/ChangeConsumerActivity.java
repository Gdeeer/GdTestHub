package com.gdeer.gdtesthub.listview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.gdeer.gdtesthub.R;

import java.util.Arrays;
import java.util.List;

public class ChangeConsumerActivity extends AppCompatActivity {

    private ChangeConsumerListView mListView;
    private MyAdapter mAdapter;
    private FooterLayout mFooterLayout;
    private ListFragment mListFragment;
    private WebViewFragment mWebViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_consumer);

        mListView = findViewById(R.id.lv_change_consumer);

        String s = "a a a a a a a a a a a a";
        String[] array = s.split(" ");
        List<String> list = Arrays.asList(array);
        mAdapter = new MyAdapter(list);
        mListView.setAdapter(mAdapter);
        mListView.setTagName("main");
        // 设置不拦截
        // mListView.setNeedSuperIntercept(false);

        // 给 ListView 添加一个 footerView
        mFooterLayout = (FooterLayout) LayoutInflater.from(ChangeConsumerActivity.this).inflate(R.layout.layout_footer, null);
        mListView.addFooterView(mFooterLayout);

        addWebViewFragment();
    }

    /**
     * 添加 ListFragment 到 ListView 中
     */
    private void addListFrament() {
        new Handler().post(() -> {
            mFooterLayout.setHeight(mListView.getHeight());
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (mListFragment == null || fragmentManager != null) {
                mListFragment = new ListFragment();
                mListView.setSwipeListener(() -> mListFragment.isReachTop());
                try {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.layout_footer_content, mListFragment);
                    transaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 添加 WebViewFragment 到 ListView 中
     */
    private void addWebViewFragment() {
        new Handler().post(() -> {
            mFooterLayout.setHeight(mListView.getHeight());
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (mWebViewFragment == null || fragmentManager != null) {
                mWebViewFragment = new WebViewFragment();
                mListView.setSwipeListener(() -> mWebViewFragment.isReachTop());
                try {
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.layout_footer_content, mWebViewFragment);
                    transaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
