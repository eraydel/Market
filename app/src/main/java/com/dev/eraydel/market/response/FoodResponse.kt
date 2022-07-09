package com.dev.eraydel.market.response

import com.dev.eraydel.market.model.FoodModel
import com.google.gson.annotations.SerializedName

data class FoodResponse(
    @SerializedName("food" ) var food : ArrayList<FoodModel> = arrayListOf()
)
