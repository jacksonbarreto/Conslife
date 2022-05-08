package com.conslife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.conslife.adapters.MissionAdapter
import com.conslife.databinding.CurrentMissionFragmentBinding
import com.conslife.models.Mission
import java.util.ArrayList

class CurrentMissionsFragment : Fragment() {

    private lateinit var _binding: CurrentMissionFragmentBinding
    private lateinit var missionAdapter: MissionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CurrentMissionFragmentBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        missionAdapter = MissionAdapter()
        addDataSet()
        _binding.myCurrentMissionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CurrentMissionsFragment.context)
            adapter = missionAdapter
        }
    }

    private fun addDataSet() {
        val missions = ArrayList<Mission>()
        missions.add(
            Mission(
                "Limpe a praia",
                "Praia Norte, Viana do Castelo",
                "Tudo muito limpinho",
                "22/05/2023",
                "17/08/2024",
                12,
                348,
                "data:image/jpeg;base64,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"
            )
        )
        missionAdapter.setDataSet(missions)
    }
}