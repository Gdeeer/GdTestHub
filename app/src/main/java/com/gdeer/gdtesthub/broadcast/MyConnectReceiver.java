package com.gdeer.gdtesthub.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.Set;

/**
 * 打印收到的 intent 的 extras
 */
public class MyConnectReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("zhangjl", "onReceive: " + intent);
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Set<String> set = bundle.keySet();
                for (String key : set) {
                    Log.d("zhangjl", "key: " + key + " value: " + bundle.get(key));
                }
            }
        }
    }
}
