package com.example.coffeeshop.utility

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.coffeeshop.R
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