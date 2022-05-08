package com.conslife.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.conslife.databinding.ActivityMyMissionsBinding

class MyMissionsActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMyMissionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMyMissionsBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val navHostFragment = (supportFragmentManager.findFragmentById(_binding.fragmentContainerView.id)) as NavHostFragment
        val navController = navHostFragment.navController

        _binding.myMissionMenuNavigation.setupWithNavController(navController)
    }
}