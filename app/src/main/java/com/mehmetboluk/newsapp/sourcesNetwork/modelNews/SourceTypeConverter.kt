package com.mehmetboluk.newsapp.sourcesNetwork.modelNews

import androidx.room.TypeConverter
import org.json.JSONObject

class SourceTypeConverter {
    @TypeConverter
    fun fromSource(source: Source): String {
        return JSONObject().apply {
            put("id", source.id)
            put("name", source.name)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): Source {
        val json = JSONObject(source)
        return Source(json.get("id") as String, json.getString("name"))
    }
}