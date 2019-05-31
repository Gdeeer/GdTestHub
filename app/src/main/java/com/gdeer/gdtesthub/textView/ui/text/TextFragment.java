package com.gdeer.gdtesthub.textView.ui.text;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.textView.EllipsizeTextView;

public class TextFragment extends Fragment {

    private TextViewModel mViewModel;

    private TextView mTvNormal;
    private TextView mTvEllipsize;
    private EllipsizeTextView mTvSpecial;
    private TextView mTvSpecialToNormal;
    private EditText mEditText;

    public static TextFragment newInstance() {
        return new TextFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_fragment, container, false);
        mTvNormal = view.findViewById(R.id.tv_normal);
        mTvEllipsize = view.findViewById(R.id.tv_ellipsize);
        mTvSpecial = view.findViewById(R.id.tv_special);
        mEditText = view.findViewById(R.id.editText);
        mTvSpecialToNormal = view.findViewById(R.id.tv_special_to_normal);

        mTvNormal.setText(mEditText.getText());
        mTvEllipsize.setText(mEditText.getText());
        mTvSpecial.setText(mEditText.getText());
        mTvSpecialToNormal.setText(mTvSpecial.getText());
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                //根据bitmap对象创建imageSpan对象
//                ImageSpan imageSpan = new ImageSpan(mEditText.getContext(), R.drawable.today_hot_arrow);
//                //创建SpannableString对象，以便插入imageSpan对象封装的图像
//                SpannableString spannableString = new SpannableString(s);
//                //用imageSpan对象替换icon
//                spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                mTvNormal.setText(s);
                mTvEllipsize.setText(s);
                mTvSpecial.setText(s);
                mTvSpecialToNormal.setText(mTvSpecial.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TextViewModel.class);
        // TODO: Use the ViewModel
    }

}
