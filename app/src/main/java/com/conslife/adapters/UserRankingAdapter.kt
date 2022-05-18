package com.conslife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.conslife.databinding.ItemUserRankingBinding
import com.conslife.models.UserRanking
import java.util.ArrayList

class UserRankingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: List<UserRanking> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserRankingViewHolder(
            ItemUserRankingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserRankingAdapter.UserRankingViewHolder -> {
                holder.bind(items[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setDataSet(userRanking: List<UserRanking>) {
        this.items = userRanking
    }

    class UserRankingViewHolder constructor(
        itemView: ItemUserRankingBinding
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val userPosition = itemView.usernamePosition

        fun bind(userRanking: UserRanking, position: Int) {
            userPosition.text = "#${position + 1}  ${userRanking.username}"
        }

    }
}