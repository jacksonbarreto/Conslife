package com.conslife.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conslife.R
import com.conslife.databinding.ResMissionCardBinding


class MissionCard(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private val binding: ResMissionCardBinding = ResMissionCardBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        context.obtainStyledAttributes(attrs, R.styleable.MissionCard).apply {
            getString(R.styleable.MissionCard_mission_card_title)?.let { setTitle(it) }
            getString(R.styleable.MissionCard_mission_card_date)?.let { setDate(it) }
            getString(R.styleable.MissionCard_mission_card_deadline)?.let { setDeadline(it) }
            getString(R.styleable.MissionCard_mission_card_points)?.let { setPoints(it) }
            getString(R.styleable.MissionCard_mission_card_vacancies)?.let { setVacancies(it) }
            getString(R.styleable.MissionCard_mission_card_image_src)?.let { setImage(it) }
        }.recycle()

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
        binding.missionCardMissionTitle.text = title
    }

    fun setPoints(points: String) {
        binding.missionCardFloatingPoints.setTotal(points, 1)
    }

    fun setVacancies(vacancies: String) {
        binding.missionCardVacancies.setCounter(vacancies)
    }

    fun setDate(date: String) {
        binding.missionCardDate.setTitle(date)
    }

    fun setDeadline(deadline: String) {
        binding.missionCardDeadline.setTitle(deadline)
    }
}