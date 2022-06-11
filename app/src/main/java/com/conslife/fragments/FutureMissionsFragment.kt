package com.conslife.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.conslife.R
import com.conslife.adapters.FutureMissionAdapter
import com.conslife.databinding.FutureMissionFragmentBinding
import com.conslife.models.Mission
import com.google.android.gms.maps.model.LatLng
import java.util.ArrayList

class FutureMissionsFragment : Fragment() {

    private lateinit var _binding: FutureMissionFragmentBinding
    private lateinit var futureMissionAdapter: FutureMissionAdapter
    private val navController by lazy { NavHostFragment.findNavController(this) }
    private lateinit var missions: ArrayList<Mission>

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
        getMissions()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        futureMissionAdapter =
            FutureMissionAdapter({ mission: Mission -> onPrimaryButtonClicked(mission) },
                { mission: Mission -> onSecondaryButtonClicked(mission) })
        futureMissionAdapter.setDataSet(missions)
        _binding.myFutureMissionsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FutureMissionsFragment.context)
            adapter = futureMissionAdapter
        }
    }

    private fun onSecondaryButtonClicked(mission: Mission) {
        Toast.makeText(
            this.context,
            getString(R.string.toast_cancel_success, mission.title),
            Toast.LENGTH_SHORT
        ).show()
        missions.remove(mission)
        futureMissionAdapter.notifyDataSetChanged()
    }

    private fun onPrimaryButtonClicked(mission: Mission) {
        arguments = Bundle().apply {
            putString("missionImage", mission.imageURL)
            putString("missionTitle", mission.title)
            putString("missionDescription", mission.description)
            putString("missionLocation", mission.location)
            putString("missionDate", mission.dateRealization)
            putString("missionDeadline", mission.deadline)
            putInt("missionPoints", mission.points)
            putBoolean("missionFuture", true)
        }
        navController.navigate(
            R.id.action_futureMissionsFragment_to_missionDetailsFragment2,
            arguments
        )
    }

    private fun getMissions() {
        val dataset = ArrayList<Mission>()
        dataset.add(
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
                LatLng(41.8743, -8.15048)
            )
        )
        dataset.add(
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
        missions = dataset
    }
}