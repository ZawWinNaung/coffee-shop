package com.example.coffeeshop.data.remote.repo

import com.example.coffeeshop.core.ApiResult
import com.example.coffeeshop.data.remote.request.OrderRequestModel
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import com.example.coffeeshop.data.remote.service.NetworkService
import javax.inject.Inject

class ApiServiceRepoImpl @Inject constructor(
    private val networkService: NetworkService
) : ApiServiceRepo {
    override suspend fun getStoreInfo(): ApiResult<StoreInfoResponse> {
        return networkService.getStoreInfo()
    }

    override suspend fun getProducts(): ApiResult<List<Product>> {
        return networkService.getProducts()
    }

    override suspend fun makeOrder(request: OrderRequestModel): ApiResult<Any> {
        return networkService.makeOrder(request)
    }

}