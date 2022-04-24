package widget.controller

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.conslife.R
import com.conslife.databinding.InputTextBinding

@RequiresApi(Build.VERSION_CODES.Q)
class EditTextWidget @JvmOverloads constructor(
    context: Context, editText: AttributeSet? = null
) : RelativeLayout(context, editText) {

    private val binding: InputTextBinding = InputTextBinding.inflate(
        LayoutInflater.from(context), this, true
    )
    val defaultEditTextWidth: Int = 650

    init {
        context.obtainStyledAttributes(editText, R.styleable.EditTextAttributes).apply {
            getString(R.styleable.EditTextAttributes_title)?.let { setText(it) }
            getInt(
                R.styleable.EditTextAttributes_width,
                defaultEditTextWidth
            )?.let { setEditTextWidth(it) }
        }
    }

    fun setText(text: String) {
        this.binding.etInputText.hint = text
    }

    fun setEditTextWidth(width: Int) {
        val params = this.binding.etInputText.getLayoutParams()
        params.width = width
    }

}
