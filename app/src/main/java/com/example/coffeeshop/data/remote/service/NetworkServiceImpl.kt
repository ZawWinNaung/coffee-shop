package com.example.coffeeshop.data.remote.service

import com.example.coffeeshop.data.ApiService
import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import javax.inject.Inject

class NetworkServiceImpl @Inject constructor(private val apiService: ApiService) : NetworkService {
    override suspend fun getStoreInfo(): StoreInfoResponse {
        return try {
            apiService.getStoreInfo()
        } catch (e: Throwable) {
            throw e
        }
    }
}