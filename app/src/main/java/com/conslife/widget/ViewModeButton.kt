package com.conslife.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.conslife.R
import com.conslife.databinding.ResViewModeButtonBinding

class ViewModeButton(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    companion object {
        const val LIST_VIEW: Int = 1
        const val MAP_VIEW: Int = 2
    }

    private val binding: ResViewModeButtonBinding = ResViewModeButtonBinding.inflate(
        LayoutInflater.from(context), this, true
    )


    init {
        context.obtainStyledAttributes(attrs, R.styleable.ViewModeButton).apply {
            setViewMode(getInt(R.styleable.ViewModeButton_view_mode_type, 1))
        }.recycle()
    }

    fun setViewMode(view_mode: Int) {
        when (view_mode) {
            LIST_VIEW -> this.binding.viewModeButton.setImageResource(R.drawable.ic_list_icon)
            MAP_VIEW -> this.binding.viewModeButton.setImageResource(R.drawable.ic_map_icon)
            else -> this.binding.viewModeButton.setImageResource(R.drawable.ic_list_icon)
        }
    }

}