package com.conslife.models

import com.google.android.gms.maps.model.LatLng

data class Mission(
    var title: String,
    var location: String,
    var description: String,
    var deadline: String,
    var dateRealization: String,
    var vacancies: Int,
    var points: Int,
    var imageURL: String,
    var status: String,
    var latLong: LatLng
)
