package com.gdeer.gdtesthub.view.textview.font;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.gdeer.gdtesthub.R;

public class FontEditText extends androidx.appcompat.widget.AppCompatEditText implements TextWatcher {
    public static final String TAG = FontEditText.class.getSimpleName();

    public static final int NORMAL = Typeface.NORMAL;
    public static final int BOLD = Typeface.BOLD;
    public static final int ITALIC = Typeface.ITALIC;
    public static final int BOLD_ITALIC = Typeface.BOLD_ITALIC;
    private Typeface typeface = Typeface.DEFAULT;
    private Typeface hintTypeface = Typeface.DEFAULT;
    private int textSize = 15;
    private int hintTextSize = 15;
    private int textStyle = NORMAL;
    private int hintStyle = NORMAL;

    private int languageStyle = 0;

    private TextSize mTextSize;

    public FontEditText(Context context) {
        this(context, null);
    }

    public FontEditText(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
    }

    public FontEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);


        if (isInEditMode()) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FontEditText);

        if (a != null) {
            String typefaceStr = a.getString(R.styleable.FontEditText_typeface);
            String hintTypefaceStr = a.getString(R.styleable.FontEditText_hintTypeface);
            textSize = a.getDimensionPixelSize(R.styleable.FontEditText_textSize, (int) getTextSize());
            hintTextSize = a.getDimensionPixelSize(R.styleable.FontEditText_hintTextSize, textSize);
            textStyle = a.getInt(R.styleable.FontEditText_textStyle, getTypefaceStyle());
            hintStyle = a.getInt(R.styleable.FontEditText_hintTextStyle, getTypefaceStyle());

            int textSizeId = a.getInt(R.styleable.FontEditText_duTextSize, 0);
            mTextSize = TextSize.getSizeFromId(textSizeId);


            if (!TextUtils.isEmpty(typefaceStr)) {
                Typeface tf = FontManager.getInstance(context).getFont(typefaceStr);
                if (tf != null) {
                    typeface = tf;
                } else {
                }
            }

            if (!TextUtils.isEmpty(hintTypefaceStr)) {
                Typeface tf = FontManager.getInstance(context).getFont(hintTypefaceStr);
                if (tf != null) {
                    hintTypeface = tf;
                } else {
                }
            } else {
                hintTypeface = typeface;
            }
            a.recycle();
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

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        if (null != text) {
            setSelection(text.length());
        }
    }

    public void setText(CharSequence text, boolean setSelection) {
        super.setText(text, BufferType.EDITABLE);
        if (setSelection && null != text) {
            setSelection(text.length());
        }
    }

    private int getTypefaceStyle() {
        Typeface typeface = getPaint().getTypeface();
        return typeface != null ? typeface.getStyle() : Typeface.NORMAL;
    }
}
