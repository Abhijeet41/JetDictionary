package com.abhi41.jetdictionary.data.remote.dto


import com.abhi41.jetdictionary.domain.model.Meaning
import com.google.gson.annotations.SerializedName

data class MeaningDto(
    @SerializedName("antonyms")
    val antonyms: List<Any?>?,
    @SerializedName("definitions")
    val definitionDtos: List<DefinitionDto>,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String?,
    @SerializedName("synonyms")
    val synonyms: List<String?>?
){
    fun toMeaning(): Meaning {
        return Meaning(
            definition = definitionDtos.map { it.toDefination() },
            partOfSpeech = partOfSpeech
        )
    }
}