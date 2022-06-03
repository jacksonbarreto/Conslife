package com.conslife.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.conslife.R
import com.conslife.databinding.ResSearchBarBinding

class SearchBarConslife(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val binding: ResSearchBarBinding = ResSearchBarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.SearchBarConslife).apply {
            getString(R.styleable.SearchBarConslife_search_view_conslife_hint)?.let { setHint(it) }
        }.recycle()
        binding.searchView.setTex
        val editText =
            this.binding.searchView.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        editText.setTextColor(ContextCompat.getColor(context, R.color.conslife_magenta))
        editText.setHintTextColor(ContextCompat.getColor(context, R.color.conslife_magenta))
    }

    fun setHint(hint: String) {
        this.binding.searchView.queryHint = hint
    }
}
