package com.abhi41.jetdictionary.domain.model


data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String?,
    val word: String?
)
