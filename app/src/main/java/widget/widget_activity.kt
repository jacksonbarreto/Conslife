package widget

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import androidx.annotation.RequiresApi
import androidx.core.hardware.display.DisplayManagerCompat
import com.conslife.R
import com.conslife.databinding.ActivityWidgetBinding

class widget_activity : AppCompatActivity() {
    private lateinit var binding: ActivityWidgetBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = ActivityWidgetBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        val editText = this.binding.editTextWidget
        val defaultDisplay =
            DisplayManagerCompat.getInstance(this).getDisplay(Display.DEFAULT_DISPLAY)
        val displayContext = createDisplayContext(defaultDisplay!!)

        val width = displayContext.resources.displayMetrics.widthPixels

        editText.setText("Login")
        editText.setEditTextWidth(width - (width/4))
    }
}