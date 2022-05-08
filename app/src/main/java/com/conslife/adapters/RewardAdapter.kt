package com.conslife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conslife.R
import com.conslife.databinding.ResRewardCardBinding
import com.conslife.models.Reward
import java.util.ArrayList

class RewardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Reward> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RewardViewHolder(
            ResRewardCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RewardViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setDataSet(reward: List<Reward>) {
        this.items = reward
    }

    class RewardViewHolder constructor(
        itemView: ResRewardCardBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val rewardTitle = itemView.rewardCardTitle
        private val rewardDeadline = itemView.rewardCardDeadline
        private val rewardDescription = itemView.rewardCardDescription
        private val rewardImage = itemView.rewardCardImage
        private val rewardAvailable = itemView.rewardCardAvailable
        private val rewardPoints = itemView.rewardCardFloatingPoints

        fun bind(reward: Reward) {
            rewardTitle.text = reward.title
            rewardDeadline.text = reward.deadline
            rewardDescription.text = reward.description
            rewardAvailable.setCounter(reward.available.toString())
            rewardPoints.setTotal(reward.points.toString(), 1)
            setImage(reward.imageURL)
        }

        private fun setImage(imageURL: String) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(imageURL)
                .into(rewardImage)
        }
    }
}