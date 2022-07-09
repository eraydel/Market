package com.dev.eraydel.market.api

import com.dev.eraydel.market.response.FoodResponse
import com.dev.eraydel.market.response.ProductsResponse
import com.dev.eraydel.market.response.ServicesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {

    @GET
    suspend fun getFoodItems(@Url url: String) : Response<FoodResponse>

    @GET
    suspend fun getProductsItems(@Url url: String) : Response<ProductsResponse>

    @GET
    suspend fun getServicesItems(@Url url: String) : Response<ServicesResponse>
}