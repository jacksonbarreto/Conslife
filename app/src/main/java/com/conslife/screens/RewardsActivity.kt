package com.conslife.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conslife.R
import com.conslife.databinding.ActivityRewardsBinding

class RewardsActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityRewardsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRewardsBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}