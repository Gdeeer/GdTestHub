package com.gdeer.gdtesthub.view.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.view.recyclerview.dummy.DummyContent
import kotlinx.android.synthetic.main.fragment_item.view.*

class MyRcvAdapter(private val mValues: List<DummyContent.DummyItem>) : RecyclerView.Adapter<MyRcvAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        Log.d("zhangjl", "onCreateViewHolder ")
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("zhangjl", "onBindViewHolder position: $position holder: $holder")
        val item = mValues[position]
        holder.mTvId.text = item.id
        holder.mTvContent.text = item.content
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun getItemId(position: Int): Long {
        return mValues[position].id.toLong()
    }

    inner class MyViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mTvId: TextView = mView.tv_item_id
        val mTvContent: TextView = mView.tv_item_content

        override fun toString(): String {
            return super.toString() + " '${mTvContent.text}'"
        }
    }
}