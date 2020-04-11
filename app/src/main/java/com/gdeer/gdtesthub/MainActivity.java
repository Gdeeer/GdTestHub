package com.gdeer.gdtesthub;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.gdeer.gdtesthub.anr.AnrActivity;
import com.gdeer.gdtesthub.daydream.MyDaydreamService;
import com.gdeer.gdtesthub.db.DbActivity;
import com.gdeer.gdtesthub.view.decorview.DecorActivity;
import com.gdeer.gdtesthub.finish.FinishActivity;
import com.gdeer.gdtesthub.launchtime.LaunchTimeActivity;
import com.gdeer.gdtesthub.location.LocationActivity;
import com.gdeer.gdtesthub.poplibrary.retrofit.RetrofitActivity;
import com.gdeer.gdtesthub.simplelist.MyLauncherActivity;
import com.gdeer.gdtesthub.view.textview.TextActivity;
import com.gdeer.gdtesthub.view.viewfliper.ViewFlipperActivity;
import com.gdeer.gdtesthub.viewmodel.VmActivity;

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
            new MainItemBean("decorView", DecorActivity.class),
            new MainItemBean("ViewFlipper", ViewFlipperActivity.class),
            new MainItemBean("launchTimeTest", LaunchTimeActivity.class),
            new MainItemBean("finishTest", FinishActivity.class),
            new MainItemBean("ANRTest", AnrActivity.class),
            new MainItemBean("startDayDream", MyDaydreamService.class),
            new MainItemBean("startLocation", LocationActivity.class),
            new MainItemBean("startDB", DbActivity.class),
            new MainItemBean("startRetrofit", RetrofitActivity.class),
            new MainItemBean("startText", TextActivity.class),
            new MainItemBean("startVM", VmActivity.class),
            new MainItemBean("mainLauncher", MyLauncherActivity.class),
        };
        mDataList.addAll(Arrays.asList(list));
    }

}
