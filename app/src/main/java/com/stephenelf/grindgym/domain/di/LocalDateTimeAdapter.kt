package com.stephenelf.gymder.domain.di

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class LocalDateTimeAdapter {
    private val dateFormat = SimpleDateFormat(SERVER_FORMAT, Locale.getDefault())

    @ToJson
    fun toJson(value: Date): String {
        return dateFormat.format(value)
    }
    @FromJson
    fun fromJson(value: String): Date? {
        return dateFormat.parse(value)
    }

    //"2008-09-16T19:00:00+00:00"
    companion object {
      const val SERVER_FORMAT = ("yyyy-MM-dd'T'HH:mm:ss+HH:mm")
    }
}