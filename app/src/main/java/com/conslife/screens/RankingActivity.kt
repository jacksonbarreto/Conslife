package com.conslife.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.conslife.R
import com.conslife.databinding.ActivityRankingBinding

class RankingActivity : AppCompatActivity() {
    private lateinit var _binding : ActivityRankingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRankingBinding.inflate(layoutInflater)
        setContentView(_binding.root)
    }
}