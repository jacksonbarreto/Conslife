package com.conslife.widget

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.conslife.R
import com.conslife.databinding.ResFloatingPointsBinding

class FloatingPoints(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    companion object {
        const val PRIMARY: Int = 1
        const val SECONDARY: Int = 2
    }

    private val binding: ResFloatingPointsBinding = ResFloatingPointsBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.FloatingPoints).apply {
            val type = getInt(R.styleable.FloatingPoints_floating_points_type, 1)
            getString(R.styleable.FloatingPoints_floating_points_total)?.let { setTotal(it, type) }
            setType(type)
        }.recycle()
    }

    fun setTotal(total: String, type: Int) {
        val text = if (type == PRIMARY) "+" else ""
        "$text ${total.trim()}".also { this.binding.floatingPointsPoints.text = it }
    }

    fun setType(type: Int) {
        val color: ColorStateList? = when (type) {
            PRIMARY -> ContextCompat.getColorStateList(context, R.color.conslife_magenta)
            SECONDARY -> ContextCompat.getColorStateList(context, R.color.conslife_light_blue)
            else -> ContextCompat.getColorStateList(context, R.color.conslife_magenta)
        }
        this.binding.floatingPointsBackground.backgroundTintList = color
    }
}