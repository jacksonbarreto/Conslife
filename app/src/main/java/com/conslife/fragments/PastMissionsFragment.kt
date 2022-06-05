package com.conslife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.conslife.adapters.FutureMissionAdapter
import com.conslife.adapters.PastMissionAdapter
import com.conslife.databinding.PastMissionFragmentBinding
import com.conslife.models.Mission
import com.google.android.gms.maps.model.LatLng
import java.util.ArrayList

class PastMissionsFragment : Fragment() {

    private lateinit var _binding: PastMissionFragmentBinding
    private lateinit var pastMissionAdapter: PastMissionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PastMissionFragmentBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        pastMissionAdapter = PastMissionAdapter()
        addDataSet()
        _binding.myPastMissionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PastMissionsFragment.context)
            adapter = pastMissionAdapter
        }
    }

    private fun addDataSet() {
        val missions = ArrayList<Mission>()
        missions.add(
            Mission(
                "Cuidar do cão",
                "Barcelinhos, Barcelos",
                "Tudo muito limpinho",
                "22/05/2023",
                "17/08/2024",
                14,
                469,
                "https://guiaanimal.net/uploads/content/image/53269/Design_sem_nome__4_.png",
                "Concluída",
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
                "Pendente",
                LatLng(41.7043, -8.8148)
            )
        )
        missions.add(
            Mission(
                "Reflorestar o parque",
                "Vila do Conde, Porto",
                "Tudo muito limpinho",
                "22/05/2023",
                "17/08/2024",
                2,
                247,
                "https://essenciadoambiente.pt/wp-content/uploads/2020/09/ctt-agenda.png",
                "anulada",
                LatLng(41.7043, -8.8148)
            )
        )
        missions.add(
            Mission(
                "Cuidar do cão",
                "Meadela, Viana do Castelo",
                "Tudo muito limpinho",
                "22/05/2023",
                "17/08/2024",
                14,
                469,
                "https://guiaanimal.net/uploads/content/image/53269/Design_sem_nome__4_.png",
                "Concluída",
                LatLng(41.7043, -8.8148)
            )
        )
        missions.add(
            Mission(
                "Cuidar do cão",
                "Famalicão, Porto",
                "Tudo muito limpinho",
                "22/05/2023",
                "17/08/2024",
                14,
                469,
                "https://guiaanimal.net/uploads/content/image/53269/Design_sem_nome__4_.png",
                "Concluída",
                LatLng(41.7043, -8.8148)
            )
        )
        pastMissionAdapter.setDataSet(missions)
    }
}