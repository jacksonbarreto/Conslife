package com.conslife.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.conslife.R
import com.conslife.databinding.ResMissionCardBinding
import com.conslife.models.Mission
import java.util.ArrayList

class MissionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Mission> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return MissionViewHolder(
            ResMissionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MissionViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setDataSet(missions: List<Mission>){
        this.items = missions
    }


    class MissionViewHolder constructor(
        itemView: ResMissionCardBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val missionTitle = itemView.missionCardMissionTitle
        private val missionDeadline = itemView.missionCardDeadline
        private val missionDate = itemView.missionCardDate
        private val missionImage = itemView.missionCardImage
        private val missionVacancies = itemView.missionCardVacancies
        private val missionPoints = itemView.missionCardFloatingPoints

        fun bind(mission: Mission) {
            missionTitle.text = mission.title
            missionDeadline.setTitle(mission.deadline)
            missionDate.setTitle(mission.dateRealization)
            missionVacancies.setCounter(mission.vacancies.toString())
            missionPoints.setTotal(mission.points.toString(), 1)
            setImage(mission.imageURL)
        }

        private fun setImage(imageURL: String) {
            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(imageURL)
                .into(missionImage)
        }
    }

}