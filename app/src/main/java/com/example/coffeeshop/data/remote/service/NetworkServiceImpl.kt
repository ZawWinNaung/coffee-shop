package com.example.coffeeshop.data.remote.service

import com.example.coffeeshop.core.ApiResult
import com.example.coffeeshop.data.ApiService
import com.example.coffeeshop.data.remote.request.OrderRequestModel
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import retrofit2.Response
import javax.inject.Inject

class NetworkServiceImpl @Inject constructor(private val apiService: ApiService) : NetworkService {
    override suspend fun getStoreInfo(): ApiResult<StoreInfoResponse> {
        return try {
            ApiResult.Success(Response.success(apiService.getStoreInfo()))
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun getProducts(): ApiResult<List<Product>> {
        return try {
            ApiResult.Success(Response.success(apiService.getProducts()))
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    override suspend fun makeOrder(request: OrderRequestModel): ApiResult<Any> {
        return try {
            ApiResult.Success(Response.success(apiService.makeOrder(request)))
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}