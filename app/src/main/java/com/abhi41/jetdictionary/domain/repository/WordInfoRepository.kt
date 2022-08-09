package com.abhi41.jetdictionary.domain.repository

import com.abhi41.jetdictionary.domain.model.WordInfo
import com.abhi41.jetdictionary.util.Resource
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}