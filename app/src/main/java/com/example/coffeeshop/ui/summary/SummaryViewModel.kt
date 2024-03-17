package com.example.coffeeshop.ui.summary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeshop.core.ApiResult
import com.example.coffeeshop.data.constant.StatusCode.CREATED
import com.example.coffeeshop.data.remote.repo.ApiServiceRepo
import com.example.coffeeshop.data.remote.request.OrderRequestModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val repo: ApiServiceRepo
) : ViewModel() {

    val loading = MutableLiveData<Boolean>()

    val success = MutableLiveData<Boolean>()

    val addressError = MutableLiveData<String>()

    fun makeOrder(request: OrderRequestModel) {

        if (request.deliveryAddress.isBlank()) {
            addressError.postValue("Address should not be empty")
        } else {
            viewModelScope.launch {
                loading.postValue(true)
                when (val result = repo.makeOrder(request)) {
                    is ApiResult.Success -> {
                        loading.postValue(false)
                        success.postValue(result.data.code() == CREATED)
                    }

                    is ApiResult.Error -> {
                        loading.postValue(false)
                    }
                }
            }
        }
    }
}