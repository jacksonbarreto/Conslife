package com.conslife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.conslife.R
import com.conslife.databinding.FragmentMissionDetailsBinding


class MissionDetailsFragment : Fragment() {
    private lateinit var _binding: FragmentMissionDetailsBinding
    private val navController by lazy { NavHostFragment.findNavController(this) }
    private lateinit var missionTitle: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMissionDetailsBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("missionTitle")?.let {
            _binding.headingMission.setTitle(it)
            missionTitle = it
        }
        arguments?.getString("missionLocation")?.let {
            _binding.headingMission.setSubTitle(it)
        }
        arguments?.getString("missionDescription")?.let {
            _binding.missionDescription.text = it
        }
        arguments?.getInt("missionPoints").let {
            _binding.missionPoints.setTotal(it.toString(), 1)
        }
        arguments?.getString("missionDate")?.let {
            _binding.missionDate.setTitle(it)
        }
        arguments?.getString("missionDeadline")?.let {
            _binding.missionDeadline.setTitle(it)
        }
        _binding.buttonApply.setOnClickListener {
            _binding.buttonApply.setText(getString(R.string.btn_applied))
            _binding.buttonApply.isEnabled = false
            Toast.makeText(
                this.context,
                "${getString(R.string.toast_apply_success)} $missionTitle",
                Toast.LENGTH_SHORT
            ).show()
            navController.popBackStack()
        }
    }

}