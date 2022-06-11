package com.conslife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conslife.R
import com.conslife.databinding.FragmentRewardDetailsBinding

class RewardDetailsFragment : Fragment() {
    private lateinit var _binding: FragmentRewardDetailsBinding
    private val navController by lazy { NavHostFragment.findNavController(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRewardDetailsBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("rewardImage")?.let {
            setImage(it)
        }
        arguments?.getString("rewardTitle")?.let {
            _binding.headingReward.setTitle(it)
        }
        arguments?.getString("rewardDescription")?.let {
            _binding.rewardDescription.text = it
        }
        arguments?.getInt("rewardPoints").let {
            _binding.rewardPoints.setTotal(it.toString(), 1)
        }
        arguments?.getString("rewardDeadline")?.let {
            _binding.rewardDeadline.setTitle(it)
        }
        _binding.buttonApply.setOnClickListener {
            _binding.buttonApply.setText(getString(R.string.btn_applied))
            _binding.buttonApply.isEnabled = false
            Toast.makeText(
                this.context,
                getString(R.string.toas_reward_success),
                Toast.LENGTH_SHORT
            ).show()
            navController.popBackStack()
        }
    }

    private fun setImage(imageURL: String) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this.context!!)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageURL)
            .into(_binding.rewardImage)
    }
}