package com.gdeer.gdtesthub.view.textview.font;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;

import com.gdeer.gdtesthub.R;

/**
 * Author:    shaw
 * Version    V1.0
 * Date:      2017/7/11
 * Description:
 * Modification  History:
 * Date          Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2017/7/11       shaw             1.0
 * Why & What is modified:
 */
public class FontText extends AppCompatTextView implements TextWatcher {

    public static final int NORMAL = Typeface.NORMAL;
    public static final int BOLD = Typeface.BOLD;
    public static final int ITALIC = Typeface.ITALIC;
    public static final int BOLD_ITALIC = Typeface.BOLD_ITALIC;

    private Typeface typeface = Typeface.DEFAULT;
    private Typeface hintTypeface = Typeface.DEFAULT;
    private int textStyle = NORMAL;
    private int hintStyle = NORMAL;
    private int textSize;
    private int hintTextSize;
    private int languageStyle = 0;

    private TextSize mTextSize;

    public FontText(Context context) {
        this(context, null);
    }

    public FontText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FontText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        if (isInEditMode()) {
            return;
        }

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.FontText);

        if (ta != null) {
            String fontAsset = ta.getString(R.styleable.FontText_typeface);
            String hintFontAsset = ta.getString(R.styleable.FontText_hintTypeface);
            textStyle = ta.getInt(R.styleable.FontText_textStyle, getTypefaceStyle());
            hintStyle = ta.getInt(R.styleable.FontText_hintTextStyle, getTypefaceStyle());
            textSize = ta.getDimensionPixelSize(R.styleable.FontEditText_textSize, (int) getTextSize());
            hintTextSize = ta.getDimensionPixelSize(R.styleable.FontEditText_hintTextSize, textSize);
            languageStyle = ta.getInt(R.styleable.FontEditText_languageStyle, 0);
            int textSizeId = ta.getInt(R.styleable.FontEditText_duTextSize, 0);
            mTextSize = TextSize.getSizeFromId(textSizeId);


            if (!TextUtils.isEmpty(fontAsset)) {
                typeface = FontManager.getInstance(context).getFont(fontAsset);
                if (null == typeface) {
                    typeface = Typeface.DEFAULT;
                }
            }
            if (!TextUtils.isEmpty(hintFontAsset)) {
                hintTypeface = FontManager.getInstance(context).getFont(fontAsset);
                if (null == hintTypeface) {
                    hintTypeface = Typeface.DEFAULT;
                }
            }
            ta.recycle();
        }
        addTextChangedListener(this);
        if (hintTypeface != Typeface.DEFAULT && hintTypeface != Typeface.DEFAULT_BOLD) {
            setHint(" " + getHint());
        }
        if (null == getText() || TextUtils.isEmpty(getText().toString())) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, hintTextSize);
            setTypeface(hintTypeface, getTypefaceStyle());
        } else {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            setTypeface(typeface, getTypefaceStyle());
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (hintTypeface == null || typeface == null) {
            return;
        }
        if (s.length() == 0) {
            // no text, hint is visible
            setTypeface(hintTypeface, hintStyle);
            setTextSize(TypedValue.COMPLEX_UNIT_PX, hintTextSize);
        } else {
            // no hint, text is visible
            if (mTextSize == null) {
                setTypeface(typeface, textStyle);
                setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private int getTypefaceStyle() {
        Typeface typeface = getPaint().getTypeface();
        return typeface != null ? typeface.getStyle() : Typeface.NORMAL;
    }
}
