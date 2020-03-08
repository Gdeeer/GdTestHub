package com.gdeer.gdtesthub.listview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.gdeer.gdtesthub.R;

public class WebViewFragment extends Fragment {
    private WebView mWebView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        Log.d("zhangjl", "WebViewFragment: onCreateView");
        mWebView = view.findViewById(R.id.webview_news);
        mWebView.post(() -> mWebView.loadUrl("https://www.baidu.com/"));
        return view;
    }

    public boolean isReachTop() {
        return !mWebView.canScrollVertically(-1);
    }
}