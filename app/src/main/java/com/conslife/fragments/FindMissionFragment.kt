package com.conslife.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.conslife.R
import com.conslife.databinding.FragmentFindMissionBinding

class FindMissionFragment : Fragment() {
    private lateinit var _binding: FragmentFindMissionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindMissionBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding.btnTest.setOnClickListener {
            Log.i("DEV", "<<<<<<<<<<<<<<<<<<<<<<<< BTN Clicked >>>>>>>>>>>>>>>>>")
        }
        val btnViewMode = _binding.findMissionSelectView
        btnViewMode.setOnClickListener {
            Log.i("DEV", "<<<<<<<<<<<<<<<<<<<<<<<< Clicked >>>>>>>>>>>>>>>>>")
            changeModeView()
        }
    }

    private fun changeModeView() {
        val navController =
            NavHostFragment.findNavController(this)
        val btnViewMode = _binding.findMissionSelectView
        Log.i("DEV", "<<<<<<<<<<<<<<<<<<<<<<<< ChangMode >>>>>>>>>>>>>>>>>")

        if (btnViewMode.getViewModeType()) {
            Log.i("DEV", "<<<<<<<<<<<<<<<<<<<<<<<< TRUE >>>>>>>>>>>>>>>>>")

            navController.navigate(R.id.fragment_find_mission_map)
        } else {
            Log.i("DEV", "<<<<<<<<<<<<<<<<<<<<<<<< FALSE >>>>>>>>>>>>>>>>>")

            navController.navigate(R.id.fragment_find_mission_cards)
        }
    }

}