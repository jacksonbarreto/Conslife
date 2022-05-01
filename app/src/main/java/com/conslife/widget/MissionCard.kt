package com.conslife.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
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
        }.recycle()

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