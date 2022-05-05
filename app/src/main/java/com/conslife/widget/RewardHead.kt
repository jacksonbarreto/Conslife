package com.conslife.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.conslife.R
import com.conslife.databinding.ResRewardHeadBinding

class RewardHead(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private val binding: ResRewardHeadBinding =
        ResRewardHeadBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.obtainStyledAttributes(attrs, R.styleable.RewardHead).apply {
            setPoints(getInt(R.styleable.RewardHead_reward_head_points, 0))
        }.recycle()
    }

    fun setPoints(points: Int) {
        this.binding.rewardHeadPoints.text = points.toString()
    }
}