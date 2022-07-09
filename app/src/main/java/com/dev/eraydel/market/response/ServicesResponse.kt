package com.dev.eraydel.market.response

import com.dev.eraydel.market.model.ServicesModel
import com.google.gson.annotations.SerializedName

data class ServicesResponse(
    @SerializedName("services" ) var services : ArrayList<ServicesModel> = arrayListOf()
)
