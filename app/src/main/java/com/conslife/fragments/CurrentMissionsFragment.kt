package com.conslife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.conslife.R
import com.conslife.databinding.FragmentCurrentMissionBinding

class CurrentMissionsFragment : Fragment() {

    private lateinit var _binding: FragmentCurrentMissionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentMissionBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController =
            NavHostFragment.findNavController(this)
        _binding.buttonConfirmation.setOnClickListener{
            navController.navigate(R.id.currentMissionsFragment_to_confirmMissionQrcodeFragment)
        }
    }

}