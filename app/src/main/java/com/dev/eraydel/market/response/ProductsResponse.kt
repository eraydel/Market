package com.dev.eraydel.market.response

import com.dev.eraydel.market.model.ProductsModel
import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products" ) var products : ArrayList<ProductsModel> = arrayListOf()
)
