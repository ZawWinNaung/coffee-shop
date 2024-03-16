package com.example.coffeeshop.data.remote.service

import com.example.coffeeshop.core.ApiResult
import com.example.coffeeshop.data.remote.response.StoreInfoResponse

interface NetworkService {

    suspend fun getStoreInfo(): ApiResult<StoreInfoResponse>

}