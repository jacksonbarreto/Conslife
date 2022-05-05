package com.conslife

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conslife.databinding.ActivityMainBinding
import com.conslife.databinding.ActivityMainBinding.inflate

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)


        val a= this.binding.vmb
        a.setViewMode(2)

    }
}