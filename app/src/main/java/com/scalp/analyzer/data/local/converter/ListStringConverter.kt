package com.scalp.analyzer.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListStringConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<String> {
        return if (value == null) {
            emptyList()
        } else {
            val listType = object : TypeToken<List<String>>() {}.type
            gson.fromJson<List<String>>(value, listType)
        }
    }

    @TypeConverter
    fun toString(list: List<String>?): String {
        return gson.toJson(list ?: emptyList<String>())
    }
}
