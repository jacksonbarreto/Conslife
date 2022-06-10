package com.conslife.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.conslife.R
import com.conslife.adapters.MissionAdapter
import com.conslife.databinding.FragmentFindMissionMapBinding
import com.conslife.models.Mission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Marker

class FindMissionMapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var _binding: FragmentFindMissionMapBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var lastKnownLocation: Location
    private lateinit var missions: List<Mission>
    private var missionMarker = ArrayList<Marker>()
    private var locationPermissionGranted = false
    private lateinit var missionAdapter: MissionAdapter
    private val defaultLocation = LatLng(41.7043, -8.8148)

    companion object {
        private const val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0
        private const val DEFAULT_ZOOM = 10
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindMissionMapBinding.inflate(inflater)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        getMissions()
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        missionAdapter = MissionAdapter { mission ->
            updateSelectedMarker(mission)
            moveCamera(mission.latLong, DEFAULT_ZOOM.toFloat())
        }
        missionAdapter.setDataSet(missions)
        _binding.findMissionsCardsRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@FindMissionMapFragment.context,
                RecyclerView.HORIZONTAL,
                false
            )
            adapter = missionAdapter
        }
    }

    private fun updateSelectedMarker(mission: Mission) {
        for (marker in missionMarker) {
            if (marker.tag == mission) {
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            } else {
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        addMarkers()
        mMap.setOnMarkerClickListener { marker ->
            val mission = missions.find { it == marker.tag as Mission }
            updateCurrentVisibleMission(mission)
            updateSelectedMarker(mission!!)
            true
        }
        getLocationPermission()
        updateLocationUI()
        getDeviceLocation()
    }

    private fun updateCurrentVisibleMission(mission: Mission?) {
        return if (mission != null) {
            _binding.findMissionsCardsRecyclerView.smoothScrollToPosition(missions.indexOf(mission))
        } else {
            _binding.findMissionsCardsRecyclerView.smoothScrollToPosition(0)
        }
    }

    private fun moveCamera(latLng: LatLng, zoom: Float) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }

    private fun getDeviceLocation() {
        try {
            if (locationPermissionGranted) {
                val locationResult = fusedLocationProviderClient.lastLocation
                locationResult.addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        // Set the map's camera position to the current location of the device.
                        lastKnownLocation = task.result
                        if (lastKnownLocation != null) {
                            moveCamera(LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude), DEFAULT_ZOOM.toFloat())
                        }
                    } else {
                        Log.d("TAG", "Current location is null. Using defaults.")
                        Log.e("TAG", "Exception: %s", task.exception)
                        mMap.moveCamera(
                            CameraUpdateFactory
                                .newLatLngZoom(defaultLocation, DEFAULT_ZOOM.toFloat())
                        )
                        mMap.uiSettings.isMyLocationButtonEnabled = false
                    }
                }
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }


    private fun addMarkers() {
        missions.forEach { mission ->
            val marker = mMap.addMarker(
                MarkerOptions()
                    .title(mission.title)
                    .snippet(mission.location)
                    .position(mission.latLong)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            )
            if (marker != null) {
                marker.tag = mission
                missionMarker.add(marker)
            }
        }
    }

    override fun onMarkerClick(p0: Marker) = false

    private fun getMissions() {
        val dataset = ArrayList<Mission>()
        dataset.add(
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
                LatLng(41.009543, -8.6012)
            )
        )
        dataset.add(
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
        missions = dataset
    }


    private fun locationPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun updateLocationUI() {
        try {
            if (locationPermissionGranted()) {
                mMap.isMyLocationEnabled = true
                mMap.uiSettings.isMyLocationButtonEnabled = true
                mMap.uiSettings.isZoomControlsEnabled = true
            } else {
                mMap.isMyLocationEnabled = false
                mMap.uiSettings.isMyLocationButtonEnabled = false
                mMap.uiSettings.isZoomControlsEnabled = false
                getLocationPermission()
            }
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message, e)
        }
    }

    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationPermissionGranted = true
        } else {
            requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION
            )
        }
    }
}