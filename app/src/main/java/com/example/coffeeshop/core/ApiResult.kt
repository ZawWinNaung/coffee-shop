package com.example.coffeeshop.core

import java.lang.Exception

sealed class ApiResult<out T> {
    data class Error(val exception: Exception) : ApiResult<Nothing>()

    data class Success<T>(val data: T) : ApiResult<T>()
}