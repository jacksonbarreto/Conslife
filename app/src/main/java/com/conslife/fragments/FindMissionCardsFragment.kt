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
import com.conslife.adapters.MissionAdapter
import com.conslife.databinding.FragmentFindMissionCardsBinding
import com.conslife.models.Mission
import com.google.android.gms.maps.model.LatLng
import java.util.ArrayList

class FindMissionCardsFragment : Fragment() {
    private lateinit var _binding: FragmentFindMissionCardsBinding
    private lateinit var missionAdapter: MissionAdapter
    private val navController by lazy { NavHostFragment.findNavController(this) }
    private val missions = ArrayList<Mission>()

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
        getDataset()
        initializeRecyclerView()
    }


    private fun initializeRecyclerView() {
        missionAdapter =
            MissionAdapter(
                null,
                { mission: Mission -> onPrimaryButtonClicked(mission) },
                { mission: Mission -> onSecondaryButtonClicked(mission) })
        missionAdapter.setDataSet(missions)
        _binding.findMissionsCardsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@FindMissionCardsFragment.context)
            adapter = missionAdapter
        }
    }

    private fun onSecondaryButtonClicked(mission: Mission) {
        arguments = Bundle().apply {
            putString("missionImage", mission.imageURL)
            putString("missionTitle", mission.title)
            putString("missionDescription", mission.description)
            putString("missionLocation", mission.location)
            putString("missionDate", mission.dateRealization)
            putString("missionDeadline", mission.deadline)
            putInt("missionPoints", mission.points)

        }
        navController.navigate(R.id.action_fragment_find_mission_cards_to_missionDetailsFragment, arguments)
    }


    private fun onPrimaryButtonClicked(mission: Mission) {
        Toast.makeText(
            this.context,
            "${getString(R.string.toast_apply_success)} ${mission.title}",
            Toast.LENGTH_SHORT
        ).show()
        removeMission(mission)
    }

    private fun removeMission(mission: Mission) {
        missions.remove(mission)
        missionAdapter.notifyDataSetChanged()
    }


    private fun getDataset() {
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
                LatLng(41.7043, -8.8148)
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
        missions.addAll(dataset)
    }
}