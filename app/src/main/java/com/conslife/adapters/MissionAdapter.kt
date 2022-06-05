package com.conslife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conslife.databinding.ResItemMissionCardBinding
import com.conslife.models.Mission
import java.util.ArrayList

class MissionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<Mission> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MissionFindViewHolder(
            ResItemMissionCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MissionFindViewHolder -> {
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

    class MissionFindViewHolder constructor(
        itemView: ResItemMissionCardBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val card = itemView.card

        fun bind(mission: Mission) {
            card.setTitle(mission.title)
            card.setLocation(mission.location)
            card.setDeadline(mission.deadline)
            card.setDate(mission.dateRealization)
            card.setImage(mission.imageURL)
            card.setVacancies(mission.vacancies.toString())
            card.setPoints(mission.points.toString())
        }

    }
}