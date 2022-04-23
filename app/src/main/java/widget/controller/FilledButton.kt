package widget.controller

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.conslife.R
import com.conslife.databinding.FilledButtonBinding

class FilledButton @JvmOverloads constructor(
    context: Context, filledButton: AttributeSet? = null
) : RelativeLayout(context, filledButton) {

    private val binding: FilledButtonBinding = FilledButtonBinding.inflate(
        LayoutInflater.from(context), this, true
    )

  //  private val defaultButtonWidth:Int = 150;
  //  private val defaultButtonHeight:Int = 50;

    init {

        context.obtainStyledAttributes(filledButton, R.styleable.ButtonAttributes).apply {

            getInt(R.styleable.ButtonAttributes_buttonColor,R.color.conslife_magenta)?.let { setColor(it) }
            getString(R.styleable.ButtonAttributes_buttonText)?.let { setText(it) }
            getInt(R.styleable.ButtonAttributes_buttonWidth,150)?.let { setBtnWidthAndHeight(it,it) }
            getInt(R.styleable.ButtonAttributes_buttonHeight,150)?.let { setBtnWidthAndHeight(it,it) }
        }
    }

    fun setColor(color: Int) {
        this.binding.btnFilled.setBackgroundColor(color)
    }

    fun setText(text: String) {
        this.binding.btnFilled.text=text
    }

    fun setBtnWidthAndHeight(width: Int,height : Int) {
        this.binding.btnFilled.layoutParams = LayoutParams(width,height)
    }
}

