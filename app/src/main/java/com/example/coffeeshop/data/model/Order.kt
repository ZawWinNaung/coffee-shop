package com.example.coffeeshop.data.model

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Int,

    @SerializedName("imageUrl")
    val imageUrl: String,

    @SerializedName("quantity")
    val quantity: Int
)