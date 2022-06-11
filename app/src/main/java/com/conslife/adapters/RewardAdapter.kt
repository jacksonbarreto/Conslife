package com.conslife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conslife.R
import com.conslife.databinding.ResItemRewardCardBinding
import com.conslife.databinding.ResRewardCardBinding
import com.conslife.models.Mission
import com.conslife.models.Reward
import java.util.ArrayList

class RewardAdapter(
    private val onPrimaryButtonClicked: ((Reward) -> Unit)?,
    private val onSecondaryButtonClicked: ((Reward) -> Unit)?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Reward> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RewardViewHolder(
            ResItemRewardCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RewardViewHolder -> {
                holder.bind(items[position], onPrimaryButtonClicked, onSecondaryButtonClicked)
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
        itemView: ResItemRewardCardBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val card = itemView.card

        fun bind(
            reward: Reward,
            onPrimaryButtonClicked: ((Reward) -> Unit)?,
            onSecondaryButtonClicked: ((Reward) -> Unit)?
        ) {
            card.setTitle(reward.title)
            card.setDeadline(reward.deadline)
            card.setDescription(reward.description)
            card.setAvailable(reward.available.toString())
            card.setPoints(reward.points.toString())
            //rewardPoints.setTotal(reward.points.toString(), 1)
            card.setImage(reward.imageURL)
            card.setOnPrimaryButtonClickListener {
                if (onPrimaryButtonClicked != null) onPrimaryButtonClicked(reward)
            }
            card.setOnSecondaryButtonClickListener {
                if (onSecondaryButtonClicked != null) onSecondaryButtonClicked(reward)
            }
        }

    }
}