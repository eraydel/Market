package com.dev.eraydel.market

import com.dev.eraydel.market.model.FoodModel
import com.dev.eraydel.market.model.ProductsModel
import com.dev.eraydel.market.model.ServicesModel

interface OnItemClickListener {
    fun clickFoodItem(food: FoodModel)

    fun clickProductsItem(products: ProductsModel)

    fun clickServicesItem(services: ServicesModel)
}