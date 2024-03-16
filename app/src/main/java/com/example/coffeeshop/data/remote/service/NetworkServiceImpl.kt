package com.example.coffeeshop.data.remote.service

import com.example.coffeeshop.core.ApiResult
import com.example.coffeeshop.data.ApiService
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import javax.inject.Inject

class NetworkServiceImpl @Inject constructor(private val apiService: ApiService) : NetworkService {
    override suspend fun getStoreInfo(): ApiResult<StoreInfoResponse> {
        return try {
            ApiResult.Success(apiService.getStoreInfo())
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun getProducts(): ApiResult<List<Product>> {
        return try {
            ApiResult.Success(apiService.getProducts())
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}