package com.example.coffeeshop.data

import com.example.coffeeshop.data.remote.request.OrderRequestModel
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("storeInfo")
    suspend fun getStoreInfo(): StoreInfoResponse

    @GET("products")
    suspend fun getProducts(): List<Product>

    @POST("order")
    suspend fun makeOrder(
        @Body requestBody: OrderRequestModel
    ): Any
}