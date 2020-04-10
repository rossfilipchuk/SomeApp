package com.someapp.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ArticleEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "app_database"
        private var instance: AppDataBase? = null

        private fun create(context: Context): AppDataBase =
            Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()

        fun getInstance(context: Context): AppDataBase =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun articleDao(): ArticleDao
}
