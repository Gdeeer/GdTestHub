package com.gdeer.gdtesthub.pluginfy.androidClass;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.gdeer.gdtesthub.reflect.FieldUtils;
import com.gdeer.gdtesthub.reflect.MethodUtils;

import java.util.List;

/**
 * hook ActivityThread#mH#mCallback
 * 使其认为要启动的不是 stubActivity，而是 targetActivity
 */
public class MockHCallback implements Handler.Callback {
    private static final String TAG = "MockHCallback";

    /**
     * android 28 以下，ActivityThread$H.LAUNCH_ACTIVITY = 100
     */
    private static final int LAUNCH_ACTIVITY = 100;

    /**
     * android 28 上，ActivityThread$H.EXECUTE_TRANSACTION = 159
     */
    private static final int EXECUTE_TRANSACTION = 159;

    private Handler mBase;

    MockHCallback(Handler base) {
        mBase = base;
    }

    @Override
    public boolean handleMessage(Message msg) {
        handleLaunchActivity(msg);
        mBase.handleMessage(msg);
        return true;
    }

    private void handleLaunchActivity(Message msg) {
        Log.d(TAG, "handleLaunchActivity");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            if (msg.what == EXECUTE_TRANSACTION) {
                handleLaunchActivitySince28(msg);
            }
        } else {
            if (msg.what == LAUNCH_ACTIVITY) {
                handleLaunchActivityBefore28(msg);
            }
        }
    }

    private void handleLaunchActivityBefore28(Message msg) {
        try {
            Log.d(TAG, "handleLaunchActivityBefore28");
            Object obj = msg.obj;
            Log.d(TAG, "obj: " + obj);
            if (obj != null) {
                Intent raw = (Intent) FieldUtils.readField(obj, "intent");
                Intent target = raw.getParcelableExtra(AMSPHookHelper.EXTRA_TARGET_INTENT);
                Log.d(TAG, "target: " + target);
                if (target != null) {
                    raw.setComponent(target.getComponent());
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "handleLaunchActivityBefore28 exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleLaunchActivitySince28(Message msg) {
        try {
            Log.d(TAG, "handleLaunchActivitySince28");
            Object mActivityCallbacks = FieldUtils.readField(msg.obj, "mActivityCallbacks");
            Log.d(TAG, "mActivityCallbacks: " + mActivityCallbacks);
            if (mActivityCallbacks != null) {
                List<?> list = (List<?>) mActivityCallbacks;
                if (list.size() > 0) {
                    Object listItem = list.get(0);
                    Class classLaunchActivityItem = Class.forName("android.app.servertransaction.LaunchActivityItem");
                    if (listItem.getClass() == classLaunchActivityItem) {
                        Intent raw = (Intent) FieldUtils.readField(listItem, "mIntent");
                        Log.d(TAG, "rawIntent: " + raw);
                        Intent target = raw.getParcelableExtra(AMSPHookHelper.EXTRA_TARGET_INTENT);
                        Log.d(TAG, "targetIntent: " + target);
                        raw.setComponent(target.getComponent());
                    }
                }
            }
            // 辅助理解的代码
            Object mLifecycleStateRequest = FieldUtils.readField(msg.obj, "mLifecycleStateRequest");
            Log.d(TAG, "mLifecycleStateRequest: " + mLifecycleStateRequest);
            if (mLifecycleStateRequest != null) {
                int targetState = (int) MethodUtils.invokeMethod(mLifecycleStateRequest, "getTargetState");
                Log.d(TAG, "targetState: " + targetState);
            }
        } catch (Exception e) {
            Log.d(TAG, "handleLaunchActivitySince28 exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
