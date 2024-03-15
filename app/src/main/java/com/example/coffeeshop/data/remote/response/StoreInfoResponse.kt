package com.example.coffeeshop.data.remote.response

import com.google.gson.annotations.SerializedName

data class StoreInfoResponse(
    @SerializedName("name")
    val name: String?,

    @SerializedName("rating")
    val rating: Double?,

    @SerializedName("openingTime")
    val openingTime: String?,

    @SerializedName("closingTime")
    val closingTime: String?
)