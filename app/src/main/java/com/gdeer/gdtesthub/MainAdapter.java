package com.gdeer.gdtesthub;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ItemOnClickListener mItemClickListener;
    private List<MainItemBean> mDataList;

    public MainAdapter(List<MainItemBean> dataList) {
        mDataList = dataList;
    }

    public void setItemClickListener(ItemOnClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_main_rcv, viewGroup, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MainViewHolder mainViewHolder = (MainViewHolder) viewHolder;
        mainViewHolder.mTvDesc.setText(mDataList.get(i).getDesc());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        TextView mTvDesc;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvDesc = itemView.findViewById(R.id.tv_desc);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface ItemOnClickListener {
        void onItemClick(View view, int position);
    }
}
