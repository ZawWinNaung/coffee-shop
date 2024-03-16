package com.example.coffeeshop.data

import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import retrofit2.http.GET

interface ApiService {
    @GET("storeInfo")
    suspend fun getStoreInfo(): StoreInfoResponse

    @GET("products")
    suspend fun getProducts(): List<Product>
}