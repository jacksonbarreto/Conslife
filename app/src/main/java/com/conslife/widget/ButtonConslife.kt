package com.conslife.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.conslife.R
import com.conslife.databinding.ResButtonConslifeBinding

class ButtonConslife(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    companion object {
        const val PRIMARY_FILLED = 1
        const val PRIMARY_NOT_FILLED = 2
        const val SECONDARY_FILLED = 3
        const val SECONDARY_NOT_FILLED = 4
    }

    private val binding: ResButtonConslifeBinding = ResButtonConslifeBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.ButtonConslife).apply {
            getString(R.styleable.ButtonConslife_text)?.let { setText(it) }
            setType(getInt(R.styleable.ButtonConslife_type, 1))
        }.recycle()
    }

    fun setText(text: String) {
        this.binding.textButton.text = text
    }


    fun setType(type: Int) {
        when (type) {
            PRIMARY_FILLED -> applyType(R.color.white, R.drawable.button_primary_filled_conslife)
            PRIMARY_NOT_FILLED -> applyType(
                R.color.conslife_magenta,
                R.drawable.button_primary_not_filled_conslife
            )
            SECONDARY_FILLED -> applyType(
                R.color.white,
                R.drawable.button_secondary_filled_conslife
            )
            SECONDARY_NOT_FILLED -> applyType(
                R.color.conslife_light_blue,
                R.drawable.button_secondary_not_filled_conslife
            )
        }
    }

    private fun applyType(textColor: Int, backgroundColor: Int) {
        binding.textButton.setTextColor(
            ContextCompat.getColor(
                context,
                textColor
            )
        )
        binding.buttonShape.setBackgroundResource(backgroundColor)
    }

}