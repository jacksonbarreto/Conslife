package com.conslife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.conslife.adapters.UserRankingAdapter
import com.conslife.databinding.FragmentRankingBinding
import com.conslife.models.UserRanking

class RankingFragment : Fragment() {
    private lateinit var _binding: FragmentRankingBinding
    private lateinit var generalUserRankingAdapter: UserRankingAdapter
    private lateinit var monthlyUserRankingAdapter: UserRankingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRankingBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGeneralRecyclerView()
        initMonthlyRecyclerView()
    }

    private fun initGeneralRecyclerView() {
        generalUserRankingAdapter = UserRankingAdapter()
        addGeneralDataSet()
        _binding.generalRecyclerView.apply {
            layoutManager = GridLayoutManager(this@RankingFragment.context, 2)
            adapter = generalUserRankingAdapter
        }
    }

    private fun initMonthlyRecyclerView() {
        monthlyUserRankingAdapter = UserRankingAdapter()
        addMonthlyDataSet()
        _binding.monthlyRecyclerView.apply {
            layoutManager = GridLayoutManager(this@RankingFragment.context, 2)
            adapter = generalUserRankingAdapter
        }
    }

    private fun addGeneralDataSet() {
        val generalUsers = ArrayList<UserRanking>()
        generalUsers.add(UserRanking("jackson.barreto"))
        generalUsers.add(UserRanking("jackson.barreto"))
        generalUsers.add(UserRanking("jackson.barreto"))
        generalUsers.add(UserRanking("jackson.barreto"))
        generalUsers.add(UserRanking("jackson.barreto"))
        generalUsers.add(UserRanking("jackson.barreto"))
        generalUsers.add(UserRanking("jackson.barreto"))
        generalUsers.add(UserRanking("jackson.barreto"))
        generalUsers.add(UserRanking("jackson.barreto"))
        generalUsers.add(UserRanking("jackson.barreto"))
        generalUserRankingAdapter.setDataSet(generalUsers)
    }

    private fun addMonthlyDataSet() {
        val monthlyUsers = ArrayList<UserRanking>()
        monthlyUsers.add(UserRanking("jackson.barreto"))
        monthlyUsers.add(UserRanking("jackson.barreto"))
        monthlyUsers.add(UserRanking("jackson.barreto"))
        monthlyUsers.add(UserRanking("jackson.barreto"))
        monthlyUsers.add(UserRanking("jackson.barreto"))
        monthlyUsers.add(UserRanking("jackson.barreto"))
        monthlyUsers.add(UserRanking("jackson.barreto"))
        monthlyUsers.add(UserRanking("jackson.barreto"))
        monthlyUsers.add(UserRanking("jackson.barreto"))
        monthlyUsers.add(UserRanking("jackson.barreto"))

        monthlyUserRankingAdapter.setDataSet(monthlyUsers)
    }
}