package com.conslife.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conslife.R
import com.conslife.databinding.ResMissionCardPastBinding

class MissionCardPast(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private val binding: ResMissionCardPastBinding = ResMissionCardPastBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.MissionCardPast).apply {
            getString(R.styleable.MissionCardPast_mission_card_past_title)?.let { setTitle(it) }
            getString(R.styleable.MissionCardPast_mission_card_past_location)?.let { setLocation(it) }
            getString(R.styleable.MissionCardPast_mission_card_past_date)?.let { setDate(it) }
            getString(R.styleable.MissionCardPast_mission_card_past_points)?.let { setPoints(it) }
            getString(R.styleable.MissionCardPast_mission_card_past_image_src)?.let { setImage(it) }
            getString(R.styleable.MissionCardPast_mission_card_past_status)?.let { setStatus(it) }
        }.recycle()
    }

    fun setStatus(status: String) {
        binding.missionCardStatus.setTitle(status)
    }

    fun setImage(imageURL: String) {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(this.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(imageURL)
            .into(binding.missionCardImage)
    }

    fun setTitle(title: String) {
        binding.missionCardMissionTitle.setTitle(title)
    }

    fun setLocation(location: String) {
        binding.missionCardMissionTitle.setSubTitle(location)
    }

    fun setPoints(points: String) {
        binding.missionCardFloatingPoints.setTotal(points, 1)
    }

    fun setDate(date: String) {
        binding.missionCardDate.setTitle(date)
    }
}