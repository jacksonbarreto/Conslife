package widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conslife.R
import com.conslife.databinding.ActivityWidgetBinding
import widget.controller.FilledButton

class widget_activity : AppCompatActivity () {
    private lateinit var binding: ActivityWidgetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)

        this.binding = ActivityWidgetBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        val filledButton = this.binding.filledButtonWidget

        filledButton.setText("Josukeaa")
        filledButton.setColor(R.color.conslife_light_blue)
        filledButton.setBtnWidthAndHeight(450,250)

    }
}