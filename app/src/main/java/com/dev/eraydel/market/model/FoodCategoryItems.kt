package com.dev.eraydel.market.model

data class FoodCategoryItems(
    var id: Int, // item id
    var itemTitle: String,          //  title
    var itemDescription: String,    //  description
    var itemPrice: Int,             //  price
    var itemImage: Int,          // image
    var parent_id: Int,             // parent category
)
