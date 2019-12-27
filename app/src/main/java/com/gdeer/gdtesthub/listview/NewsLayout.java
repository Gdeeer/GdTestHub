package com.gdeer.gdtesthub.listview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class NewsLayout extends LinearLayout {
    public NewsLayout(Context context) {
        super(context);
    }

    public NewsLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHeight(int height) {
        if (getLayoutParams() == null) {
            setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT, height));
        } else {
            getLayoutParams().height = height;
        }
    }
}
