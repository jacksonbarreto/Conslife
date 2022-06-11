package com.conslife.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conslife.R
import com.conslife.databinding.ResItemMissionCardFutureBinding
import com.conslife.databinding.ResMissionCardBinding
import com.conslife.models.Mission
import java.util.ArrayList

class FutureMissionAdapter(
    private val onPrimaryButtonClicked: ((Mission) -> Unit)?,
    private val onSecondaryButtonClicked: ((Mission) -> Unit)?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Mission> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MissionViewHolder(
            ResItemMissionCardFutureBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MissionViewHolder -> {
                holder.bind(items[position], onPrimaryButtonClicked, onSecondaryButtonClicked)
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
        itemView: ResItemMissionCardFutureBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val card = itemView.card

        fun bind(
            mission: Mission,
            onPrimaryButtonClicked: ((Mission) -> Unit)?,
            onSecondaryButtonClicked: ((Mission) -> Unit)?
        ) {
            card.setTitle(mission.title)
            card.setLocation(mission.location)
            card.setDeadline(mission.deadline)
            card.setDate(mission.dateRealization)
            card.setImage(mission.imageURL)
            card.setVacancies(mission.vacancies.toString())
            card.setPoints(mission.points.toString())
            card.setOnPrimaryButtonClickListener {
                if (onPrimaryButtonClicked != null) onPrimaryButtonClicked(mission)
            }
            card.setOnSecondaryButtonClickListener {
                if (onSecondaryButtonClicked != null) onSecondaryButtonClicked(mission)
            }
        }

    }

}