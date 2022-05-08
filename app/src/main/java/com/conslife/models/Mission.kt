package com.conslife.models

data class Mission(
    var title: String,
    var location: String,
    var description: String,
    var deadline: String,
    var dateRealization: String,
    var vacancies: Int,
    var points: Int,
    var imageURL: String,
)
