package com.example.coffeeshop.data.remote.repo

import com.example.coffeeshop.core.ApiResult
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.data.remote.response.StoreInfoResponse

interface ApiServiceRepo {

    suspend fun getStoreInfo(): ApiResult<StoreInfoResponse>

    suspend fun getProducts(): ApiResult<List<Product>>
}