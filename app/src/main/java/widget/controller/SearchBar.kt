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
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.conslife.R
import com.conslife.databinding.ResSearchBarBinding

@RequiresApi(Build.VERSION_CODES.Q)
class SearchBar @JvmOverloads constructor(
    context: Context, search_bar: AttributeSet? = null
) : RelativeLayout(context, search_bar) {

    private val binding: ResSearchBarBinding = ResSearchBarBinding.inflate(
        LayoutInflater.from(context), this, true)

    init {
        context.obtainStyledAttributes(search_bar, R.styleable.SearchBarAttributes).apply{
            getInt(R.styleable.SearchBarAttributes_searchWidth,700)?.let{setSearchViewWidth(it)}
            getString(R.styleable.SearchBarAttributes_hint)?.let{setHint(it)}
        }

    }

    fun setSearchViewWidth(width: Int) {
          this.binding.searchView.maxWidth = width
    }

    fun setHint(hint: String) {
        this.binding.searchView.queryHint = hint


    }


}
