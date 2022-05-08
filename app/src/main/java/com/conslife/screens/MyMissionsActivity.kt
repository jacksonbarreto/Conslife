package com.conslife.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conslife.R
import com.conslife.databinding.ActivityMyMissionsBinding

class MyMissionsActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMyMissionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMyMissionsBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}