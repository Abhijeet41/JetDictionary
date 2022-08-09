package com.abhi41.jetdictionary.domain.use_case

import com.abhi41.jetdictionary.domain.model.WordInfo
import com.abhi41.jetdictionary.domain.repository.WordInfoRepository
import com.abhi41.jetdictionary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoCase(
    private val repository: WordInfoRepository
) {
    operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow {}
        }
        return repository.getWordInfo(word)
    }
}