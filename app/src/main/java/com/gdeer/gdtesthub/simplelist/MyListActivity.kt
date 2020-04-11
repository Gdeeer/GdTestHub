package com.gdeer.gdtesthub.simplelist

import android.app.ListActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MyListActivity : ListActivity() {
    val data = listOf("今天", "明天", "后天")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        listAdapter = arrayAdapter
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        Toast.makeText(this, data[position], Toast.LENGTH_SHORT).show()
    }
}