package com.conslife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conslife.databinding.ResItemMissionCardPastBinding
import com.conslife.models.Mission
import java.util.ArrayList

class PastMissionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Mission> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return PastMissionAdapter.MissionViewHolder(
            ResItemMissionCardPastBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PastMissionAdapter.MissionViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setDataSet(missions: List<Mission>) {
        this.items = missions
    }

    class MissionViewHolder constructor(
        itemView: ResItemMissionCardPastBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val card = itemView.card

        fun bind(mission: Mission) {
            card.setTitle(mission.title)
            card.setLocation(mission.location)
            card.setDate(mission.dateRealization)
            card.setImage(mission.imageURL)
            card.setPoints(mission.points.toString())
            card.setStatus(mission.status)
        }

    }
}