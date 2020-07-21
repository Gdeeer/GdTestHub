package com.gdeer.gdtesthub.view.textview.edit

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gdeer.gdtesthub.R
import kotlinx.android.synthetic.main.fragment_edit.*


class EditFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): EditFragment {
            return EditFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 固定前缀的 EditText
        val prefix = "to"
        et_prefix.setText(prefix)
        et_prefix.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s == null) {
                    return
                }
                if (!s.startsWith(prefix)) {
                    // set editText with "To" again like has been initialized
                    et_prefix.setText(prefix)
                    // to make cursor in end of text
                    et_prefix.setSelection(et_prefix.text?.length ?: 0)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        btn_clear.setOnClickListener {
            et_prefix.setText("")
        }

        btn_change.setOnClickListener {
            et_prefix.text = et_text.text
        }
    }
}