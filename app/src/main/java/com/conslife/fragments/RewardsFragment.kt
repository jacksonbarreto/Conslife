package com.conslife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.conslife.adapters.PastMissionAdapter
import com.conslife.adapters.RewardAdapter
import com.conslife.databinding.FragmentRewardsBinding
import com.conslife.models.Reward

class RewardsFragment : Fragment() {
    private lateinit var _binding: FragmentRewardsBinding
    private lateinit var rewardAdapter: RewardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRewardsBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBalance()
        initRecyclerView()
    }

    private fun getBalance(){
        _binding.rewardHeadCard.setPoints(439)
    }

    private fun initRecyclerView() {
        rewardAdapter = RewardAdapter()
        addDataSet()
        _binding.rewardsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@RewardsFragment.context)
            adapter = rewardAdapter
        }
    }

    private fun addDataSet() {
        val rewards = ArrayList<Reward>()
        rewards.add(
            Reward(
                "Viagem a lua",
                "Um vaucher para uma viagem a lua, na nave espacial da SpaceX. Com direito a foto com o Elon.",
                "17/08/2022",
                3,
                750,
                "https://s2.glbimg.com/tL-DJY_yabbMbSHg-W4jMvwoHgU=/0x0:2048x1365/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_59edd422c0c84a879bd37670ae4f538a/internal_photos/bs/2021/y/R/Vlwdr2RyeEzJi8IpkmVA/51307180163-57b88b31af-k.jpg"
            )
        )
        rewards.add(
            Reward(
                "Livro: Clean Architecture",
                "Livro Clean Architecture, autografado pelo Uncle Bob.",
                "09/06/2023",
                14,
                120,
                "https://i.pinimg.com/originals/ba/28/de/ba28deb63a5dc11b51dbd2b5233fb52d.jpg"
            )
        )
        rewards.add(
            Reward(
                "Bagaço Caseiro",
                "Uma dose dupla de bagaço caseiro original, da aldeia de Fragoso.",
                "12/05/2022",
                35,
                68,
                "https://3.bp.blogspot.com/-3SaOF0b4JU0/WnGgmi2uMNI/AAAAAAAAD08/Xys-74cdnCMGqoxHpvjpRi3R7iZYHOAHgCLcBGAs/s1600/baga%25C3%25A7o.jpg"
            )
        )
        rewardAdapter.setDataSet(rewards)
    }
}