package com.abhi41.jetdictionary.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abhi41.jetdictionary.data.local.Converters
import com.abhi41.jetdictionary.data.local.dao.WordInfoDao
import com.abhi41.jetdictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class WordInfoDatabase : RoomDatabase() {
    abstract val dao: WordInfoDao
}