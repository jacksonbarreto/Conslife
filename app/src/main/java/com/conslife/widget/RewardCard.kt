package com.conslife.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conslife.R
import com.conslife.databinding.ResRewardCardBinding

class RewardCard(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private val binding: ResRewardCardBinding = ResRewardCardBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.RewardCard).apply {
            getString(R.styleable.RewardCard_reward_card_title)?.let { setTitle(it) }
            getString(R.styleable.RewardCard_reward_card_description)?.let { setDescription(it) }
            getString(R.styleable.RewardCard_reward_card_deadline)?.let { setDeadline(it) }
            getString(R.styleable.RewardCard_reward_card_available)?.let { setAvailable(it) }
            getString(R.styleable.RewardCard_reward_card_image_src)?.let { setImage(it) }
            getString(R.styleable.RewardCard_reward_card_points)?.let { setPoints(it) }
        }.recycle()
    }

    fun setImage(imageURL: String) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageURL)
            .into(binding.rewardCardImage)
    }

    fun setTitle(title: String) {
        binding.rewardCardTitle.text = title
    }

    fun setPoints(points: String) {
        binding.rewardCardFloatingPoints.setTotal(points, 2)
    }

    fun setAvailable(vacancies: String) {
        binding.rewardCardAvailable.setCounter(vacancies)
    }

    fun setDeadline(deadlineDate: String) {
        val deadlineMsg = context.getString(R.string.reward_card_deadline) + " $deadlineDate"
        binding.rewardCardDeadline.text = deadlineMsg
    }

    fun setDescription(description: String) {
        binding.rewardCardDescription.text = description
    }

    fun setOnPrimaryButtonClickListener(function: () -> Unit) {
        binding.rewardCardBtnReserve.setOnClickListener { function() }
    }

    fun setOnSecondaryButtonClickListener(function: () -> Unit) {
        binding.rewardCardBtnKnowMore.setOnClickListener { function() }
    }
}