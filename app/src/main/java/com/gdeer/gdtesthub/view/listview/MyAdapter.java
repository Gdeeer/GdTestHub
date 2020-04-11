package com.gdeer.gdtesthub.view.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gdeer.gdtesthub.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<String> mDataList;

    public MyAdapter(List<String> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        MyViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_siple_listview, parent, false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.mTv.setText(mDataList.get(position));
        return convertView;
    }

    class MyViewHolder {
        TextView mTv;

        MyViewHolder(View view) {
            mTv = view.findViewById(R.id.tv_item_content);
        }
    }
}
