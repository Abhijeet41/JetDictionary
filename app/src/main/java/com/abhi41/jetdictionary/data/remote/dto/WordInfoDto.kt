package com.abhi41.jetdictionary.data.remote.dto


import com.abhi41.jetdictionary.data.local.entity.WordInfoEntity
import com.abhi41.jetdictionary.domain.model.WordInfo
import com.google.gson.annotations.SerializedName

data class WordInfoDto(
    @SerializedName("meanings")
    val meanings: List<MeaningDto>?,
    @SerializedName("phonetic")
    val phonetic: String?,
    @SerializedName("phonetics")
    val phonetics: List<PhoneticDto>?,
    @SerializedName("sourceUrls")
    val sourceUrls: List<String>?,
    @SerializedName("word")
    val word: String?
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings?.map { it.toMeaning() } ?: emptyList(),
            phonetic = phonetic ?: "null",
            word = word ?: "null"
        )
    }

}