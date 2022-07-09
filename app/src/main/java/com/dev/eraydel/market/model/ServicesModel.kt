package com.dev.eraydel.market.model

data class ServicesModel(
    var id: Int,
    var title: String,
    var description: String,
    var image: String,
    var category: String,
    var owner: String,
    var resume: String,
    var experience: String,
    var schedule: String,
    var address: String,
    var telephone: String,
    var location: ArrayList<LocationModel>
)
