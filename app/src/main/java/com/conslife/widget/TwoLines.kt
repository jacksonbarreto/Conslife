package com.conslife.widget

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import com.conslife.databinding.ResTwoLinedTextViewBinding

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.conslife.R
import com.conslife.databinding.ResFloatingPointsBinding

class TwoLines(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val binding: ResTwoLinedTextViewBinding = ResTwoLinedTextViewBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private val firstLineDefaulSize: Float = 24F
    private val secondLineDefaulSize: Float = 18F

    private val firstLineIsBold: Boolean = false
    private val secondLineIsBold: Boolean = true

    init {
        context.obtainStyledAttributes(attrs, R.styleable.TwoLines).apply {

            getString(R.styleable.TwoLines_line_one_text)?.let { setFirstLineText(it) }
            getString(R.styleable.TwoLines_line_two_text)?.let { setSecondLineText(it) }

            getFloat(
                R.styleable.TwoLines_line_one_font_size,
                firstLineDefaulSize
            )?.let { setFirstLineFontSize(it) }
            getFloat(
                R.styleable.TwoLines_line_two_font_size,
                secondLineDefaulSize
            )?.let { setSecondLineFontSize(it) }


            getBoolean(
                R.styleable.TwoLines_line_one_text_style,
                firstLineIsBold
            )?.let { setFirstLineTextStyle(it) }
            getBoolean(
                R.styleable.TwoLines_line_two_text_style,
                secondLineIsBold
            )?.let { setSecondLineTextStyle(it) }

            getString(
                R.styleable.TwoLines_line_one_text_color
            )?.let { setFirstLineTextColor(it) }
            getString(
                R.styleable.TwoLines_line_two_text_color
            )?.let { setSecoundLineTextColor(it) }
        }
    }

    fun setFirstLineText(text: String) {
        this.binding.tvFistLine.setText(text)
    }

    fun setFirstLineFontSize(fontSize: Float) {
        this.binding.tvFistLine.textSize = fontSize as Float
    }

    fun setFirstLineTextStyle(isBold: Boolean) {
        if (isBold)
            this.binding.tvFistLine.typeface = Typeface.DEFAULT_BOLD

    }

    fun setFirstLineTextColor(color: String) {
        this.binding.tvFistLine.setTextColor(Color.parseColor(color))
    }

    fun setSecondLineText(text: String) {
        this.binding.tvSecondLine.setText(text)

    }

    fun setSecondLineFontSize(fontSize: Float) {
        this.binding.tvSecondLine.textSize = fontSize as Float

    }

    fun setSecondLineTextStyle(isBold: Boolean) {
        if (isBold)
            this.binding.tvSecondLine.typeface = Typeface.DEFAULT_BOLD
    }

    fun setSecoundLineTextColor(color: String) {
        this.binding.tvSecondLine.setTextColor(Color.parseColor(color))

    }
}


