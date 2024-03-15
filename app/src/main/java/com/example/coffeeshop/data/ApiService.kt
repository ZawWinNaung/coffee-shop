package com.example.coffeeshop.data

import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import retrofit2.http.GET

interface ApiService {
    @GET("storeInfo")
    suspend fun getStoreInfo() : StoreInfoResponse
}