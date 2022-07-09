package com.dev.eraydel.market.model

data class FoodModel(
    var id: Int? = null,
    var title: String,
    var description: String,
    var image: String,
    var category: String,
    var owner: String,
    var schedule: String,
    var address: String,
    var telephone: String,
    var location: ArrayList<LocationModel>,
    var menu: ArrayList<MenuModel>
)
