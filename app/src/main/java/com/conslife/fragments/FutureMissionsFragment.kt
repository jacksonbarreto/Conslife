package com.conslife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.conslife.adapters.FutureMissionAdapter
import com.conslife.databinding.FutureMissionFragmentBinding
import com.conslife.models.Mission
import java.util.ArrayList

class FutureMissionsFragment : Fragment() {

    private lateinit var _binding: FutureMissionFragmentBinding
    private lateinit var futureMissionAdapter: FutureMissionAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FutureMissionFragmentBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        futureMissionAdapter = FutureMissionAdapter()
        addDataSet()
        _binding.myFutureMissionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FutureMissionsFragment.context)
            adapter = futureMissionAdapter
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
                "pendente"
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
                "pendente"
            )
        )
        futureMissionAdapter.setDataSet(missions)
    }
}