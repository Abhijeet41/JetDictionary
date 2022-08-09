package com.abhi41.jetdictionary.domain.model


data class Meaning(
    val definition: List<Defination>,
    val partOfSpeech: String?,
)
