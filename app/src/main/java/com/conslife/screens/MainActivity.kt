package com.conslife.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.conslife.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val navHostFragment =
            (supportFragmentManager
                .findFragmentById(_binding.MainFragmentContainerView.id)) as NavHostFragment
        val navController = navHostFragment.navController
        _binding.mainMenu.setupWithNavController(navController)
    }
}