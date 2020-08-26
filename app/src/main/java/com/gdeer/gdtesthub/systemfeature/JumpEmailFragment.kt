package com.gdeer.gdtesthub.systemfeature

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.gdeer.gdtesthub.BaseFragment
import com.gdeer.gdtesthub.R

class JumpEmailFragment : BaseFragment() {
    override fun getLayout(): Int {
        return R.layout.fragment_jump_email
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = Intent(Intent.ACTION_SENDTO)
        data.data = Uri.parse("mailto:way.ping.li@gmail.com")
        data.putExtra(Intent.EXTRA_SUBJECT, "这是标题")
        data.putExtra(Intent.EXTRA_TEXT, "这是内容")
        startActivity(data)
    }
}