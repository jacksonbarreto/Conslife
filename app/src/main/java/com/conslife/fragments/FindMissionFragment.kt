package com.conslife.fragments

import android.os.Bundle
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
        val btnViewMode = _binding.findMissionSelectView

        btnViewMode.setOnClickListener {
            changeModeView()
        }

    }

    private fun changeModeView() {
        val navController =
            NavHostFragment.findNavController(_binding.findMissionFragmentContainerView.getFragment())
        val btnViewMode = _binding.findMissionSelectView

        btnViewMode.changeModeView()
        if (btnViewMode.getViewModeType()) {
            navController.navigate(R.id.fragment_find_mission_map)
        } else {
            navController.navigate(R.id.fragment_find_mission_cards)
        }
    }

}