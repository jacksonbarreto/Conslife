package com.conslife.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
        arguments?.getString("missionImage")?.let {
            setImage(it)
        }
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
        arguments?.getBoolean("missionFuture", false)?.let {
            _binding.buttonApply.visibility = if (it) View.GONE else View.VISIBLE
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

    fun setImage(imageURL: String) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this.context!!)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageURL)
            .into(_binding.missionImage)
    }

}