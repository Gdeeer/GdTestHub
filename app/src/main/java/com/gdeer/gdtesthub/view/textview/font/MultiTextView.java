package com.gdeer.gdtesthub.view.textview.font;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.gdeer.gdtesthub.R;

public class MultiTextView extends TextView {

    private String beforeText = "";
    private float beforeTextSize = 0;
    private int beforeTextColor = 0;
    private String laterText = "";
    private float laterTextSize = 0;
    private int laterTextColor = 0;

    private OnClickListener beforeTextOnClickListener;
    private OnClickListener laterTextOnClickListener;

    public MultiTextView(Context context) {
        super(context);
    }

    public MultiTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initFontText(context, attrs);
    }

    public MultiTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFontText(context, attrs);
    }


    private void initFontText(Context context, AttributeSet attrs) {
        if (isInEditMode())
            return;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FontText);

        if (ta != null) {
            String fontAsset = ta.getString(R.styleable.FontText_typeface);

            if (!TextUtils.isEmpty(fontAsset)) {
                Typeface tf = FontManager.getInstance(context).getFont(fontAsset);
                int style = Typeface.NORMAL;


                if (getTypeface() != null)
                    style = getTypeface().getStyle();

                if (tf != null)
                    setTypeface(tf, style);
            }
        }
    }


    private void notifyBeforeText() {
        SpannableString spanStr = new SpannableString(getBeforeText());
        if (!TextUtils.isEmpty(getBeforeText())) {
            if (getBeforeTextSize() != 0)
                spanStr.setSpan(new AbsoluteSizeSpan((int) getBeforeTextSize()), 0, getBeforeText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (getBeforeTextColor() != 0)
                spanStr.setSpan(new ForegroundColorSpan(getBeforeTextColor()), 0, getBeforeText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (getBeforeTextOnClickListener() != null) {
                spanStr.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        getBeforeTextOnClickListener().onClick(widget, getBeforeText());
                    }
                }, 0, getBeforeText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        append(spanStr);
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void notifyLaterText() {
        SpannableString spanStr = new SpannableString(getLaterText());
        if (!TextUtils.isEmpty(getLaterText())) {
            if (getLaterTextSize() != 0)
                spanStr.setSpan(new AbsoluteSizeSpan((int) getLaterTextSize()), 0, getLaterText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (getLaterTextColor() != 0)
                spanStr.setSpan(new ForegroundColorSpan(getLaterTextColor()), 0, getLaterText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            if (getLaterTextOnClickListener() != null)
                spanStr.setSpan(new ClickableSpan() {
                    @Override
                    public void onClick(View widget) {
                        getLaterTextOnClickListener().onClick(widget, getLaterText());
                    }
                }, 0, getLaterText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        append(spanStr);
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void appendText(String text) {
        append(text);
    }

    public void appendText(String text, boolean isStrike) {
        SpannableString spanStr = new SpannableString(text);
        if (isStrike) {
            spanStr.setSpan(new StrikethroughSpan(), 0, text.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        append(spanStr);
    }

    public void appendMultiText(final String text, final int textColor, int textSize, final OnClickListener listener) {
        SpannableString spanStr = new SpannableString(text);
        if (textSize != 0)
            spanStr.setSpan(new AbsoluteSizeSpan(textSize), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (textColor != 0)
            spanStr.setSpan(new ForegroundColorSpan(textColor), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (listener != null) {
            spanStr.setSpan(new ClickableSpan() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    if (textColor != 0) {
                        ds.setColor(textColor);
                    }
                    ds.setUnderlineText(false);
                }

                @Override
                public void onClick(View widget) {
                    listener.onClick(widget, text);
                }
            }, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
        append(spanStr);
//        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void appendMultiText(final String text, final int textColor, float textSizeForProportion, final OnClickListener listener) {
        SpannableString spanStr = new SpannableString(text);
        if (textSizeForProportion != 1)
            spanStr.setSpan(new RelativeSizeSpan(textSizeForProportion), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (textColor != 0)
            spanStr.setSpan(new ForegroundColorSpan(textColor), 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        if (listener != null) {
            spanStr.setSpan(new ClickableSpan() {
                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    if (textColor != 0) {
                        ds.setColor(textColor);
                    }
                    ds.setUnderlineText(false);
                }

                @Override
                public void onClick(View widget) {
                    listener.onClick(widget, text);
                }
            }, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            setMovementMethod(LinkMovementMethod.getInstance());
        }
        append(spanStr);
//        setMovementMethod(LinkMovementMethod.getInstance());
    }

    public interface OnClickListener {
        void onClick(View view, String text);
    }

    private void avoidHintColor(View view) {
        if (view instanceof TextView)
            ((TextView) view).setHighlightColor(getResources().getColor(android.R.color.transparent));
    }

    public String getBeforeText() {
        if (beforeText == null) {
            beforeText = "";
        }
        return beforeText;
    }

    public void setBeforeText(String beforeText) {
        this.beforeText = beforeText;
    }

    public float getBeforeTextSize() {
        return beforeTextSize;
    }

    public void setBeforeTextSize(float beforeTextSize) {
        this.beforeTextSize = beforeTextSize;
    }

    public int getBeforeTextColor() {
        return beforeTextColor;
    }

    public void setBeforeTextColor(int beforeTextColor) {
        this.beforeTextColor = beforeTextColor;
    }

    public String getLaterText() {
        if (laterText == null) {
            laterText = "";
        }
        return laterText;
    }

    public void setLaterText(String laterText) {
        this.laterText = laterText;
    }

    public float getLaterTextSize() {
        return laterTextSize;
    }

    public void setLaterTextSize(float laterTextSize) {
        this.laterTextSize = laterTextSize;
    }

    public int getLaterTextColor() {
        return laterTextColor;
    }

    public void setLaterTextColor(int laterTextColor) {
        this.laterTextColor = laterTextColor;
    }

    public OnClickListener getBeforeTextOnClickListener() {
        return beforeTextOnClickListener;
    }

    public void setBeforeTextOnClickListener(OnClickListener beforeTextOnClickListener) {
        this.beforeTextOnClickListener = beforeTextOnClickListener;
    }

    public OnClickListener getLaterTextOnClickListener() {
        return laterTextOnClickListener;
    }

    public void setLaterTextOnClickListener(OnClickListener laterTextOnClickListener) {
        this.laterTextOnClickListener = laterTextOnClickListener;
    }
}
