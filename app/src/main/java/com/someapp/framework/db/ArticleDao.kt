package com.someapp.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.someapp.domain.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = REPLACE)
    suspend fun save(article: ArticleEntity)

    @Delete
    suspend fun delete(article: ArticleEntity)

    @Query("SELECT * FROM articles WHERE articleTitle == :articleTitle")
    suspend fun loadByTitle(articleTitle: String): Article?

    @Query("SELECT * FROM articles")
    fun loadAll(): List<Article>
}