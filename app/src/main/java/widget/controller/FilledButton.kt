package widget.controller

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.conslife.R
import com.conslife.databinding.FilledButtonBinding

@RequiresApi(Build.VERSION_CODES.Q)
class FilledButton @JvmOverloads constructor(
    context: Context, filledButton: AttributeSet? = null
) : RelativeLayout(context, filledButton) {

    private val binding: FilledButtonBinding = FilledButtonBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private val defaultButtonWidth: Int = 350;
    private val defaultButtonHeight: Int = 150;

    //This variable exists because the width and height must forcedly be inserted togheter
    //So on the seek of a better reutilization and independency this var is saved after inserting
    //The button width on the setWidth function and there is set the default height button.
    //After that, if you just use the set setHeight function, the previous width value would be lost
    //This value ensures that this doesnt happens.
    private var insertedWidth = defaultButtonWidth

    init {
        context.obtainStyledAttributes(filledButton, R.styleable.ButtonAttributes).apply {

            getInt(R.styleable.ButtonAttributes_buttonColor,R.color.conslife_light_blue)?.let { setColor(it,it) }

            getString(R.styleable.ButtonAttributes_buttonText)?.let { setText(it) }

            getInt(R.styleable.ButtonAttributes_buttonWidth, defaultButtonWidth)?.let { setBtnWidth(it) }

            getInt(R.styleable.ButtonAttributes_buttonHeight,defaultButtonHeight)?.let { setBtnHeight(it) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("ResourceAsColor")
    fun setColor(color: Int,isFilled : Int) {
        this.binding.btnFilled.backgroundTintList = AppCompatResources.getColorStateList(context,color)
    }

    fun setText(text: String) {
        this.binding.btnFilled.text = text
    }

    fun setBtnWidth(width: Int) {
        this.binding.btnFilled.layoutParams = LayoutParams(width, 500)
        this.insertedWidth = width
    }

    fun setBtnHeight(height: Int) {
        this.binding.btnFilled.layoutParams = LayoutParams(insertedWidth, height)
    }
}

