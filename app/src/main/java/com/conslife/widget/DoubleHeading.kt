package com.conslife.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.conslife.R
import com.conslife.databinding.ResDoubleHeadingBinding

class DoubleHeading(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val binding: ResDoubleHeadingBinding = ResDoubleHeadingBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    companion object {
        const val H1: Int = 1
        const val H2: Int = 2
        const val H3: Int = 3
        const val H4: Int = 4
        const val H5: Int = 5
        const val H6: Int = 6
        const val INCREMENT = 2F
        const val TITLE_DEFAULT_SIZE = 16F
        const val SUB_TITLE_DEFAULT_SIZE = 12F
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.DoubleHeading).apply {
            getString(R.styleable.DoubleHeading_double_heading_title)?.let { setTitle(it) }
            getString(R.styleable.DoubleHeading_double_heading_sub_title)?.let { setSubTitle(it) }
            setSize(getInt(R.styleable.DoubleHeading_double_heading_size, 1))
        }.recycle()
    }

    fun setTitle(title: String) {
        this.binding.DoubleHeadingTitle.text = title
    }

    fun setSubTitle(subTitle: String) {
        this.binding.DoubleHeadingSubTitle.text = subTitle
    }

    fun setSize(size: Int) {
        when (size) {
            H2 -> applySize(H2)
            H3 -> applySize(H3)
            H4 -> applySize(H4)
            H5 -> applySize(H5)
            H6 -> applySize(H6)
            else -> applySize(H1)
        }
    }

    private fun applySize(size: Int) {
        this.binding.DoubleHeadingTitle.textSize = TITLE_DEFAULT_SIZE + (INCREMENT * size)
        this.binding.DoubleHeadingSubTitle.textSize = SUB_TITLE_DEFAULT_SIZE + (INCREMENT * size)
    }
}


