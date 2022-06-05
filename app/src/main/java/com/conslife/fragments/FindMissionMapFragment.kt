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
import androidx.fragment.app.Fragment
import com.conslife.R
import com.conslife.databinding.FragmentFindMissionMapBinding
import com.conslife.models.Mission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class FindMissionMapFragment: Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private lateinit var _binding: FragmentFindMissionMapBinding
    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private lateinit var missions: List<Mission>
    companion object{
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val ZOOM_DEFAULT = 5f
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFindMissionMapBinding.inflate(inflater)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMissions()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        setUpMap()
        addMarkers(googleMap)
        Log.i("JACKSON", "<<<<<<<<<<<<<<<<<<<<<<< MAPA PRONTO >>>>>>>>>>>>>>>>>>>>>")
        mMap.setOnMarkerClickListener {
            loadCard(it) }
        // Add a marker in Sydney and move the camera
        val target = LatLng(41.7043, -8.8148)
        val mark = MarkerOptions().position(target).title("Marker in Sydney")
       // mMap.addMarker(mark)
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(target))
      //  mMap.setMinZoomPreference(2F)

    }

    private fun loadCard(marker: Marker): Boolean {
        Log.i("JACKSON", "<<<<<<<<<<<<<<<<<<<<<<< clicked >>>>>>>>>>>>>>>>>>>>>")
        Log.i("JACKSON", marker.tag.toString())
        return true
    }

    private fun setUpMap(){
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),  LOCATION_PERMISSION_REQUEST_CODE)
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return
        }
        mMap.isMyLocationEnabled = false
        fusedLocationClient.lastLocation.addOnSuccessListener(requireActivity()) { location ->
            if (location != null){
                lastLocation = location
                val currentLatLong = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLong, ZOOM_DEFAULT))
            }
        }
    }

    private fun addMarkers(googleMap: GoogleMap){
        missions.forEach { mission ->
            val marker = googleMap.addMarker(
                MarkerOptions()
                    .title(mission.title)
                    .snippet(mission.location)
                    .position(mission.latLong)
            )
            if (marker != null) {
                marker.tag = mission
            }
        }
    }

    override fun onMarkerClick(p0: Marker) = false

    private fun getMissions(){
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
        dataset.add(    Mission(
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
        ))
        missions = dataset
    }
}