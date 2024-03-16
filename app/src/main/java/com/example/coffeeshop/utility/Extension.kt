package com.example.coffeeshop.utility

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