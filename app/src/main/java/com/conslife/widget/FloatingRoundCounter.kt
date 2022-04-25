package com.conslife.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.conslife.R
import com.conslife.databinding.ResFloatingRoundCounterBinding

class FloatingRoundCounter(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    companion object {
        const val PRIMARY: Int = 1
        const val SECONDARY: Int = 2
    }

    private val binding: ResFloatingRoundCounterBinding = ResFloatingRoundCounterBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.FloatingRoundCounter).apply {
            getString(R.styleable.FloatingRoundCounter_floating_round_counter_title)?.let {
                setTitle(
                    it
                )
            }
            getString(R.styleable.FloatingRoundCounter_floating_round_counter_total)?.let {
                setCounter(
                    it
                )
            }
            setType(getInt(R.styleable.FloatingRoundCounter_floating_round_counter_type, 1))
        }.recycle()
    }

    fun setTitle(title: String) {
        this.binding.floatingRoundCounterTitle.text = title
    }

    fun setCounter(counter: String) {
        this.binding.floatingRoundCounterCounter.text = counter
    }

    fun setType(type: Int) {
        val color: ColorStateList? = when (type) {
            PRIMARY -> ContextCompat.getColorStateList(context, R.color.conslife_magenta)
            SECONDARY -> ContextCompat.getColorStateList(context, R.color.conslife_light_blue)
            else -> ContextCompat.getColorStateList(context, R.color.conslife_magenta)
        }
        this.binding.floatingRoundCounterBackground.backgroundTintList = color
    }
}