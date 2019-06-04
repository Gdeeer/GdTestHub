package com.gdeer.gdtesthub.anr;

import com.gdeer.gdtesthub.R;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AnrActivity extends AppCompatActivity {

    Button mButton;
    BroadcastReceiver registerReceiver = new MyReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);

        mButton = (Button) findViewById(R.id.tv_sleep);
    }

    //-----------------------------------------------------------------------------------------------------------
    //触发 ANR
    //-----------------------------------------------------------------------------------------------------------

    /**
     * 点击后，阻塞主线程，用户操作
     *
     * 再连续点击空白处多次（mix2 上需4次），10s 左右有 ANR 的log，再过 10s 左右弹框
     * 或点击返回键（mix2 上只需1次），10s 左右会有 ANR 的 log，再过 10s 左右弹框
     *
     * zte n881e 上，怎么都不 ANR，其他的方式没有试
     */
    public void produceANR(View view) {
        sleepTest();
    }

    /**
     * 点击后，阻塞主线程，再启动一个 Service
     *
     * 20s 左右会有 ANR 的log，再过 10s 左右有 ANR 的弹框
     */
    public void produceANRByService(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                Intent intent = new Intent(AnrActivity.this, MyService.class);
                startService(intent);
            }
        }).start();

        sleepTest();
    }


    /**
     * 点击后，启动一个 Service，阻塞主线程
     *
     * 20s 左右会有 ANR 的log，再过 10s 左右有 ANR 的弹框
     */
    public void produceANRByServiceHandle(View view) {
        Intent intent = new Intent(AnrActivity.this, MyService.class);
        startService(intent);
    }

    /**
     * 点击后，阻塞主线程，注册并发送一个自定义 BroadCast
     *
     * 发送广播后，60s 左右有 ANR 的 log，再过 10s 左右弹框（自定义广播默认是后台广播）
     */
    public void produceANRByOwnBroadCast(View view) {
        IntentFilter filter = new IntentFilter();
        filter.addAction("anr_test");
        registerReceiver(registerReceiver, filter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(2000);
                Intent intent = new Intent("anr_test");
                sendBroadcast(intent);
            }
        }).start();

        sleepTest();
    }

    /**
     * 点击后，阻塞主线程，注册一个系统 BroadCast （如 ACTION_TIME_TICK）
     *
     * 系统发送广播后，10s 左右有 ANR 的 log，再过 10s 左右弹框
     */
    public void produceANRBySystemBroadCast(View view) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(registerReceiver, filter);

        sleepTest();
    }


    /**
     * 点击后，注册一个 BroadCast （如 ACTION_TIME_TICK），在广播的处理内阻塞主线程
     *
     * 收到广播后，10s 左右有 ANR 的 log，再过 10s 左右弹框
     */
    public void produceANRByBroadCastHandle(View view) {
        IntentFilter filter = new IntentFilter();
        filter.addAction("anr_test");
        filter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(registerReceiver, filter);

        Intent intent = new Intent("anr_test");
        intent.setFlags(Intent.FLAG_RECEIVER_FOREGROUND);
        sendBroadcast(intent);
    }


    //-----------------------------------------------------------------------------------------------------------
    //创造 ANR 环境
    //-----------------------------------------------------------------------------------------------------------

    /**
     * sleep
     */
    public void sleepTest() {
        SystemClock.sleep(100000);
    }

    /**
     * wait
     */
    public void waitTest() {
        String s = "";
        synchronized (s) {
            try {
                s.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * synchronized
     */
    public void synchronizedTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedInThread();
            }
        }).start();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                synchronizedInMain();
            }
        });
    }

    public synchronized void synchronizedInThread() {
        SystemClock.sleep(30000);
    }

    public synchronized void synchronizedInMain() {
    }

    /**
     * staticSynchronized
     */
    public void staticSynchronizedTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Util.get();
            }
        }).start();
        Util.get();
    }

    /**
     * 无限循环
     */
    public void forTest() {
        for (; ; ) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(registerReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
