package com.gdeer.gdtesthub;

import com.gdeer.gdtesthub.anr.AnrActivity;
import com.gdeer.gdtesthub.dayDream.MyDaydreamService;
import com.gdeer.gdtesthub.db.DbActivity;
import com.gdeer.gdtesthub.finish.FinishActivity;
import com.gdeer.gdtesthub.launchTime.LaunchActivity;
import com.gdeer.gdtesthub.location.LocationActivity;
import com.gdeer.gdtesthub.retrofit.RetrofitActivity;
import com.gdeer.gdtesthub.textView.TextActivity;
import com.gdeer.gdtesthub.viewModel.VmActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rcv_main)
    RecyclerView rcvMain;

    private MainAdapter mAdapter;
    private List<MainItemBean> mDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDataList();
        mAdapter = new MainAdapter(mDataList);
        mAdapter.setItemClickListener(new MainAdapter.ItemOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MainItemBean bean = mDataList.get(position);
                startIntent(bean);
            }
        });
        rcvMain.setLayoutManager(new LinearLayoutManager(this));
        rcvMain.setAdapter(mAdapter);
    }

    private void startIntent(MainItemBean bean) {
        Log.d("zhangjl", "startIntent " + System.currentTimeMillis());
        Class clazz = bean.getClazz();
        Intent intent = new Intent(this, clazz);
        try {
            startActivity(intent);
        } catch (Exception e) {
            startService(intent);
        }
    }

    private void initDataList() {
        MainItemBean[] list = new MainItemBean[]{
            new MainItemBean("launchTest", LaunchActivity.class),
            new MainItemBean("finishTest", FinishActivity.class),
            new MainItemBean("ANRTest", AnrActivity.class),
            new MainItemBean("startDayDream", MyDaydreamService.class),
            new MainItemBean("startLocation", LocationActivity.class),
            new MainItemBean("startDB", DbActivity.class),
            new MainItemBean("startRetrofit", RetrofitActivity.class),
            new MainItemBean("startText", TextActivity.class),
            new MainItemBean("startVM", VmActivity.class),
        };
        mDataList.addAll(Arrays.asList(list));
    }

}
