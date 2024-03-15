package com.example.coffeeshop.data.remote.repo

import com.example.coffeeshop.data.remote.response.StoreInfoResponse

interface ApiServiceRepo {

    suspend fun getStoreInfo(): StoreInfoResponse
}