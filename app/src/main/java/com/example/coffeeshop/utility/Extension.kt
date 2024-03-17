package com.example.coffeeshop.utility

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.coffeeshop.R
import com.example.coffeeshop.data.model.Order
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.TimeZone

fun String?.formatTime(): String {
    this?.let {
        val parser = SimpleDateFormat("hh:mm:ss")
        val dateTime = parser.parse(it)
        dateTime?.let { time ->
            val formatter = SimpleDateFormat("hh:mm a")
            formatter.timeZone = TimeZone.getDefault()
            return formatter.format(time)
        } ?: run {
            return ""
        }
    } ?: run {
        return ""
    }
}

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_coffee)
        .into(this)
}

fun Any.toJson(): String = Gson().toJson(this)

inline fun <reified T> String.toObject(): T? {
    return try {
        val gson = Gson()
        gson.fromJson(this, object : TypeToken<T>() {})
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}