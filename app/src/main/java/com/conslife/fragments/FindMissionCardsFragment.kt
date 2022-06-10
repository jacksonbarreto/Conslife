package com.conslife.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.conslife.adapters.MissionAdapter
import com.conslife.databinding.FragmentFindMissionCardsBinding
import com.conslife.models.Mission
import com.google.android.gms.maps.model.LatLng
import java.util.ArrayList

class FindMissionCardsFragment : Fragment() {
    private lateinit var _binding: FragmentFindMissionCardsBinding
    private lateinit var missionAdapter: MissionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindMissionCardsBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        missionAdapter = MissionAdapter{}
        addDataSet()
        _binding.findMissionsCardsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FindMissionCardsFragment.context)
            adapter = missionAdapter
        }
    }



    private fun addDataSet() {
        val missions = ArrayList<Mission>()
        missions.add(
            Mission(
                "Reflorestar o parque",
                "Campo belo, Barcelos",
                "Tudo muito limpinho",
                "22/05/2023",
                "17/08/2024",
                2,
                247,
                "https://essenciadoambiente.pt/wp-content/uploads/2020/09/ctt-agenda.png",
                "pendente",
                LatLng(41.7043, -8.8148)
            )
        )
        missions.add(
            Mission(
                "Cuidar do cão",
                "Marinha, Esposende",
                "Tudo muito limpinho",
                "22/05/2023",
                "17/08/2024",
                14,
                469,
                "https://guiaanimal.net/uploads/content/image/53269/Design_sem_nome__4_.png",
                "pendente",
                LatLng(41.7043, -8.8148)
            )
        )
        missionAdapter.setDataSet(missions)
    }
}