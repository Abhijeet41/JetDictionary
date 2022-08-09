package com.abhi41.jetdictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.abhi41.jetdictionary.data.local.util.JsonParser
import com.abhi41.jetdictionary.domain.model.Meaning
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
    // private val jsonParser: GsonParser  //we don't need to this whatever converts use moshi, gson etc
    // private val jsonParser: MoshiParser
) {
    @TypeConverter
    fun fromMeaningJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json = json,
            type = object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningJson(meanings: List<Meaning>): String{
        return jsonParser.toJson(
            meanings,
            type = object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
    }

}