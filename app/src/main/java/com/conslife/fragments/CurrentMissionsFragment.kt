package com.conslife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.conslife.adapters.FutureMissionAdapter
import com.conslife.databinding.CurrentMissionFragmentBinding
import com.conslife.models.Mission
import java.util.ArrayList

class CurrentMissionsFragment : Fragment() {

    private lateinit var _binding: CurrentMissionFragmentBinding
    private lateinit var futureMissionAdapter: FutureMissionAdapter

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
        futureMissionAdapter = FutureMissionAdapter()
        addDataSet()
        _binding.myCurrentMissionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@CurrentMissionsFragment.context)
            adapter = futureMissionAdapter
        }
    }

    private fun addDataSet() {
        val missions = ArrayList<Mission>()
        missions.add(
            Mission(
                "Limpe a praia 2",
                "Centro, Viana do Castelo",
                "Tudo muito limpinho",
                "22/05/2023",
                "17/08/2024",
                12,
                348,
                "https://www.melhoresdestinos.com.br/wp-content/uploads/2020/04/praias-para-relaxar.jpg"
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
                "https://guiaanimal.net/uploads/content/image/53269/Design_sem_nome__4_.png"
            )
        )
        futureMissionAdapter.setDataSet(missions)
    }
}