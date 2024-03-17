package com.example.coffeeshop.data.remote.response

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("name")
    val name: String?,

    @SerializedName("price")
    val price: Int?,

    @SerializedName("imageUrl")
    val imageUrl: String?
)