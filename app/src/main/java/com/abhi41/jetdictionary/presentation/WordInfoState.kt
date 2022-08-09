package com.abhi41.jetdictionary.presentation

import com.abhi41.jetdictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems: List<WordInfo> = emptyList(),
    val isLoading: Boolean = false
)
