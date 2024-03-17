package com.example.coffeeshop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.core.ApiResult
import com.example.coffeeshop.data.model.Order
import com.example.coffeeshop.data.remote.repo.ApiServiceRepo
import com.example.coffeeshop.data.remote.response.Product
import com.example.coffeeshop.data.remote.response.StoreInfoResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ApiServiceRepo
) : ViewModel() {

    private val _storeInfo = MutableLiveData<StoreInfoResponse>()
    val storeInfo: LiveData<StoreInfoResponse>
        get() = _storeInfo

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>>
        get() = _productList

    val loading = MutableLiveData<Boolean>()

    val orderList = mutableListOf<Order>()

    init {
        getStoreInfo()
        getProducts()
    }

    fun getStoreInfo() {
        viewModelScope.launch {
            loading.postValue(true)
            when (val result = repo.getStoreInfo()) {
                is ApiResult.Success -> {
                    loading.postValue(false)
                    _storeInfo.postValue(result.data.body())
                }

                is ApiResult.Error -> {
                    loading.postValue(false)
                }
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            loading.postValue(true)
            when (val result = repo.getProducts()) {
                is ApiResult.Success -> {
                    loading.postValue(false)
                    _productList.postValue(result.data.body())
                }

                is ApiResult.Error -> {
                    loading.postValue(false)
                }
            }
        }
    }
}