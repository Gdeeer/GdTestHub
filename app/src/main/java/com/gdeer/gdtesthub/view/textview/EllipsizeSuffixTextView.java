package com.gdeer.gdtesthub.view.textview;

import android.content.Context;
import androidx.appcompat.widget.AppCompatTextView;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Ellipsize 时带后缀的 TextView
 */
public class EllipsizeSuffixTextView extends AppCompatTextView {

    // 来自 android.text.TextUtils
    private static final String ELLIPSIS_NORMAL = "\u2026"; // (…)
    public static final String ELLIPSIS_FILLER = "\uFEFF";  // 空白字符

    String suffix = ">>";

    public EllipsizeSuffixTextView(Context context) {
        super(context);
    }

    public EllipsizeSuffixTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EllipsizeSuffixTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

        Layout layout = getLayout();
        if (layout != null) {
            // 输入的原始文字
            String rawText = String.valueOf(layout.getText());
            Log.d("zhangjl", "rawText:" + rawText + " length:" + rawText.length());
            if (rawText.contains(ELLIPSIS_NORMAL)) {
                // 去除空白字符，TextView 自带的 ellipsize 效果就是将超出长度的字符转为空白字符
                String trimText = trimBlankChar(rawText);
                Log.d("zhangjl", "trimText:" + trimText + " length:" + trimText.length());
                String targetText = trimText;
                String resultText = "";
                // 去除的字符数
                int reduceCount = 1;
                while (!resultText.equals(targetText)) {
                    targetText = rawText.subSequence(0, trimText.length() - reduceCount) + ELLIPSIS_NORMAL + suffix;
                    Log.d("zhangjl", "targetText:" + targetText + " length:" + targetText.length() +
                        " reduceCount:" + reduceCount);
                    super.setText(targetText, type);
                    layout = getLayout();
                    resultText = trimBlankChar(String.valueOf(layout.getText()));
                    Log.d("zhangjl", "resultText:" + resultText + " length:" + resultText.length() +
                        " reduceCount:" + reduceCount);
                    reduceCount++;
                }
            }
        }
    }

    public String trimBlankChar(String rawText) {
        int index = rawText.indexOf(ELLIPSIS_FILLER);
        String trimText = rawText;
        if (index > 0) {
            trimText = rawText.substring(0, index);
        }
        return trimText;
    }
}
