package com.abhi41.jetdictionary.data.remote.dto


import com.abhi41.jetdictionary.domain.model.Defination
import com.google.gson.annotations.SerializedName

data class DefinitionDto(
    @SerializedName("antonyms")
    val antonyms: List<String?>?,
    @SerializedName("definition")
    val definition: String?,
    @SerializedName("example")
    val example: String?,
    @SerializedName("synonyms")
    val synonyms: List<String?>?
){
    fun toDefination(): Defination{
        return Defination(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}