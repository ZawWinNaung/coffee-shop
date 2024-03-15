package com.example.coffeeshop.data.remote.repo

import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import com.example.coffeeshop.data.remote.service.NetworkService
import javax.inject.Inject

class ApiServiceRepoImpl @Inject constructor(
    private val networkService: NetworkService
) : ApiServiceRepo {
    override suspend fun getStoreInfo(): StoreInfoResponse {
        return networkService.getStoreInfo()
    }
}