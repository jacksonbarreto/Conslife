package com.conslife.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.conslife.R
import com.conslife.databinding.ResSearchBarBinding
import com.conslife.databinding.ResViewModeButtonBinding

class ViewModeButton(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private val binding: ResViewModeButtonBinding = ResViewModeButtonBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    companion object {
        const val listView: Int = 1
        const val mapView: Int = 2
    }

    init {
        context.obtainStyledAttributes(attrs, R.styleable.ViewMode).apply {
            getInt(R.styleable.ViewMode_view_mode, 2)?.let { setViewMode(it) }
        }
    }

    fun setViewMode(mode: Int) {
        when (mode) {
            listView -> setButtonIcon(listView)
            mapView -> setButtonIcon(mapView)
            else -> setButtonIcon(listView)
        }
    }

    private fun setButtonIcon(icon: Int) {
        if (icon == 1) {
            this.binding.viewModeButton.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_list_icon,
                0,
                0,
                0
            )

            println("Number 1")

        } else if (icon == 2) {
            this.binding.viewModeButton.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_map_icon,
                0,
                0,
                0
            )
            println("Number 2")
        }
    }
}