package com.abhi41.jetdictionary.di

import android.app.Application
import androidx.room.Room
import com.abhi41.jetdictionary.data.local.Converters
import com.abhi41.jetdictionary.data.local.database.WordInfoDatabase
import com.abhi41.jetdictionary.data.local.util.GsonParser
import com.abhi41.jetdictionary.data.remote.DictionaryApi
import com.abhi41.jetdictionary.data.repository.WordInfoRepositoryImpl
import com.abhi41.jetdictionary.domain.repository.WordInfoRepository
import com.abhi41.jetdictionary.domain.use_case.GetWordInfoCase
import com.abhi41.jetdictionary.util.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideDictionaryApi(): DictionaryApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)
    }


    @Provides
    @Singleton
    fun provideWordInfoDatabase(app: Application): WordInfoDatabase {
        return Room.databaseBuilder(
            app,
            WordInfoDatabase::class.java,
            Constants.DATABASE_NAME
        ).addTypeConverter(Converters(GsonParser(Gson()))).build()
    }

    @Provides
    @Singleton
    fun provideWordInfoRepository(
        database: WordInfoDatabase,
        api: DictionaryApi
    ): WordInfoRepository {
        return WordInfoRepositoryImpl(api = api, dao = database.dao)
    }

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfoCase {
        return GetWordInfoCase(repository)
    }


}