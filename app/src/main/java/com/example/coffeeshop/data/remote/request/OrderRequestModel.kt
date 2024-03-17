package com.example.coffeeshop.data.remote.request

import com.example.coffeeshop.data.remote.response.Product
import com.google.gson.annotations.SerializedName

data class OrderRequestModel(
    @SerializedName("products")
    val products: List<Product>,
    @SerializedName("delivery_address")
    val deliveryAddress: String
)