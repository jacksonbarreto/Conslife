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
import com.conslife.R
import com.conslife.databinding.SearchBarBinding

@RequiresApi(Build.VERSION_CODES.Q)
class SearchBar @JvmOverloads constructor(
    context: Context, search_bar: AttributeSet? = null
) : RelativeLayout(context, search_bar) {

    private val binding: SearchBarBinding = SearchBarBinding.inflate(
        LayoutInflater.from(context), this, true)
    


    init {
        context.obtainStyledAttributes(search_bar, R.styleable.SearchBarAttributes).apply{
            getInt(R.styleable.SearchBarAttributes_searchWidth,300)?.let{setSearchViewWidth(it)}
        }

    }


    fun setSearchViewWidth(width: Int) {
          this.binding.searchView.maxWidth = width
    }

    fun setHeight(color: Int) {
        //  this.binding.btnFilled.backgroundTintList = AppCompatResources.getColorStateList(context,color)
    }

    fun setHint(color: String) {
        //  this.binding.btnFilled.backgroundTintList = AppCompatResources.getColorStateList(context,color)
    }

}
