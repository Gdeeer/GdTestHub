package com.gdeer.gdtesthub.view.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gdeer.gdtesthub.R
import com.gdeer.gdtesthub.view.recyclerview.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_rcv.*

class RcvActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rcv)

        val adapter1 = MyRcvAdapter(DummyContent.ITEMS)
        adapter1.setHasStableIds(true)
        with(rcv_demo) {
            val linearLayoutManager = LinearLayoutManager(context)
            // 设置 viewCacheSize 为 0
            setItemViewCacheSize(0)
            // 禁用 prefetch 功能
            linearLayoutManager.initialPrefetchItemCount = 0
            layoutManager = linearLayoutManager
//            // 设置 pool 的 type、size 为 0
//            recycledViewPool.setMaxRecycledViews(0, 0)
            adapter = adapter1
        }
    }
}
