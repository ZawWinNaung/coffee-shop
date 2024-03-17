package com.example.coffeeshop.core

import retrofit2.Response
import java.lang.Exception

sealed class ApiResult<out T> {
    data class Error(val exception: Exception) : ApiResult<Nothing>()

    data class Success<T>(val data: Response<T>) : ApiResult<T>()
}