package com.sakthi.cleanarchitectureapp.core

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.sakthi.cleanarchitectureapp.feature_home.data.local.NewsDAO
import com.sakthi.cleanarchitectureapp.feature_home.data.local.entity.NewsEntity

@Database(
    entities = [NewsEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getNewsDAO(): NewsDAO

}