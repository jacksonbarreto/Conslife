package widget

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.conslife.R
import com.conslife.databinding.ActivityWidgetBinding

class  widget_activity : AppCompatActivity () {
    private lateinit var binding: ActivityWidgetBinding

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)

        this.binding = ActivityWidgetBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        val filledButton = this.binding.filledButtonWidget

        filledButton.setText("Aplicar")
        filledButton.setColor(R.color.conslife_magenta)
        filledButton.setBtnWidth(450)
        filledButton.setBtnHeight(200)

    }
}