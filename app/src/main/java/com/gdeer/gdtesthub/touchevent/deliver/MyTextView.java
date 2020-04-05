package com.gdeer.gdtesthub.touchevent.deliver;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * "main@11086" prio=5 tid=0x2 nid=NA runnable
 * java.lang.Thread.State: RUNNABLE
 * // ViewGroup
 * at com.gdeer.gdtesthub.touchevent.deliver.MyTextView.dispatchTouchEvent(MyTextView.java:25)
 * at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:3030)
 * at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2662)
 * at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:3030)
 * at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2662)
 * at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:3030)
 * at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2662)
 * at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:3030)
 * at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2662)
 * <p>
 * // DecorView.superDispatchTouchEvent
 * at com.android.internal.policy.DecorView.superDispatchTouchEvent(DecorView.java:440)
 * at com.android.internal.policy.PhoneWindow.superDispatchTouchEvent(PhoneWindow.java:1830)
 * <p>
 * // Activity
 * at android.app.Activity.dispatchTouchEvent(Activity.java:3400)
 * at com.gdeer.gdtesthub.touchevent.deliver.TouchDeliverActivity.dispatchTouchEvent(TouchDeliverActivity.kt:18)
 * <p>
 * // DecorView.dispatchTouchEvent
 * at com.android.internal.policy.DecorView.dispatchTouchEvent(DecorView.java:398)
 * at android.view.View.dispatchPointerEvent(View.java:12752)
 * <p>
 * // ViewRootImpl
 * at android.view.ViewRootImpl$ViewPostImeInputStage.processPointerEvent(ViewRootImpl.java:5106)
 * at android.view.ViewRootImpl$ViewPostImeInputStage.onProcess(ViewRootImpl.java:4909)
 * at android.view.ViewRootImpl$InputStage.deliver(ViewRootImpl.java:4426)
 * at android.view.ViewRootImpl$InputStage.onDeliverToNext(ViewRootImpl.java:4479)
 * at android.view.ViewRootImpl$InputStage.forward(ViewRootImpl.java:4445)
 * at android.view.ViewRootImpl$AsyncInputStage.forward(ViewRootImpl.java:4585)
 * at android.view.ViewRootImpl$InputStage.apply(ViewRootImpl.java:4453)
 * at android.view.ViewRootImpl$AsyncInputStage.apply(ViewRootImpl.java:4642)
 * at android.view.ViewRootImpl$InputStage.deliver(ViewRootImpl.java:4426)
 * at android.view.ViewRootImpl$InputStage.onDeliverToNext(ViewRootImpl.java:4479)
 * at android.view.ViewRootImpl$InputStage.forward(ViewRootImpl.java:4445)
 * at android.view.ViewRootImpl$InputStage.apply(ViewRootImpl.java:4453)
 * at android.view.ViewRootImpl$InputStage.deliver(ViewRootImpl.java:4426)
 * at android.view.ViewRootImpl.deliverInputEvent(ViewRootImpl.java:7092)
 * at android.view.ViewRootImpl.doProcessInputEvents(ViewRootImpl.java:7061)
 * at android.view.ViewRootImpl.enqueueInputEvent(ViewRootImpl.java:7022)
 * <p>
 * // InputEventReceiver
 * at android.view.ViewRootImpl$WindowInputEventReceiver.onInputEvent(ViewRootImpl.java:7195)
 * at android.view.InputEventReceiver.dispatchInputEvent(InputEventReceiver.java:186)
 * <p>
 * // Looper
 * at android.os.MessageQueue.nativePollOnce(MessageQueue.java:-1)
 * at android.os.MessageQueue.next(MessageQueue.java:326)
 * at android.os.Looper.loop(Looper.java:160)
 * at android.app.ActivityThread.main(ActivityThread.java:6669)
 * at java.lang.reflect.Method.invoke(Method.java:-1)
 * at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:493)
 * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:858)
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
