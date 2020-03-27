package com.gdeer.gdtesthub.textView;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;

/**
 * ellipsize 后进行一次重试
 * 把被截断的第一个字符加回来，重新设置一遍；解决 mate30 上 ellipsize 时截断字符过多问题
 */
public class EllipsizeReTryTextView extends AppCompatTextView {

    // 来自 android.text.TextUtils
    private static final String ELLIPSIS_NORMAL = "\u2026"; // (…)

    public EllipsizeReTryTextView(Context context) {
        super(context);
    }

    public EllipsizeReTryTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EllipsizeReTryTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 把被截断的第一个字符加回来，重新设置一遍；解决 mate30 上截断字符过多问题
     */
    @Override
    public void setText(CharSequence text, BufferType type) {
        Log.d("zhangjl", "EllipsizeReTryTextView originText:" + text + " length:" + text.length());
        super.setText(text, type);
        // 有时需在 handler 里获取，否则 getLayout() 可能返回空
        Layout layout = getLayout();
        if (layout != null && layout.getText() != null) {
            String layoutText = layout.getText().toString();
            Log.i("zhangjl", "EllipsizeReTryTextView layoutText:" + layoutText + " length:" + layoutText.length());
            if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(layoutText)
                && text.length() == layoutText.length()
                && !TextUtils.equals(text, layoutText)
                && layoutText.contains(ELLIPSIS_NORMAL)) {
                // layoutText 和 text 不相等，且 layoutText 包含省略符，则认为发生了截断
                int index = layoutText.indexOf(ELLIPSIS_NORMAL);
                // 把被截断的第一个字符加回来，再重新设置一遍
                CharSequence preText = text.subSequence(0, index + 1);
                CharSequence targetText = preText + ELLIPSIS_NORMAL;
                Log.i("zhangjl", "EllipsizeReTryTextView targetText:" + targetText + " length:" + targetText.length());
                EllipsizeReTryTextView.super.setText(targetText, type);
            }
        }
    }
}
