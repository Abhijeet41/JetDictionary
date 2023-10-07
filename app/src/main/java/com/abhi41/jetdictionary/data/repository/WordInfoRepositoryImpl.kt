package com.abhi41.jetdictionary.data.repository

import com.abhi41.jetdictionary.data.local.dao.WordInfoDao
import com.abhi41.jetdictionary.data.remote.DictionaryApi
import com.abhi41.jetdictionary.data.remote.dto.WordInfoDto
import com.abhi41.jetdictionary.domain.model.WordInfo
import com.abhi41.jetdictionary.domain.repository.WordInfoRepository
import com.abhi41.jetdictionary.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos: List<WordInfo> = dao.getWordInfos(word = word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        //make api request and insert api response data into the database
        try {
            val remoteWordInfos: List<WordInfoDto> = api.getWordInfo(word = word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word ?: "" })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })

        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = wordInfos
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.!",
                    data = wordInfos
                )
            )
        }

        //emit data to the ui ()
        //now send data from database to use-case or view-model
        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))

    }
}