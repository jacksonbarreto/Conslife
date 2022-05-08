package com.conslife.models

data class Reward(
    var title: String,
    var description: String,
    var deadline: String,
    var available: Int,
    var points: Int,
    var imageURL: String,
)
