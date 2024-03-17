package com.example.coffeeshop.data.mapper

import com.example.coffeeshop.data.model.Order
import com.example.coffeeshop.data.remote.response.Product

object ProductsMapper {
    val mapToProduct: (List<Order>) -> (List<Product>) = { result ->
        val productList = mutableListOf<Product>()

        result.forEach {
            productList.add(
                Product(
                    name = it.name,
                    price = it.price,
                    imageUrl = it.imageUrl
                )
            )
        }

        productList
    }
}