package com.someapp.data

import com.someapp.domain.Article

class ArticleRepository(private val articleDataSource: ArticleDataSource) {
    suspend fun loadDbByTitle(title : String) = articleDataSource.loadDbByTitle(title)

    suspend fun loadDb() = articleDataSource.loadDb()

    suspend fun saveArticle(article: Article) = articleDataSource.save(article)

    suspend fun deleteArticle(article: Article) = articleDataSource.delete(article)

    suspend fun load() = articleDataSource.load()
}