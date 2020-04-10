package com.someapp.data

import com.someapp.domain.Article

interface ArticleDataSource {
    suspend fun loadDbByTitle(title: String): Article?

    suspend fun loadDb(): List<Article>

    suspend fun save(article: Article)

    suspend fun delete(article: Article)

    suspend fun load() : ResourceState<List<Article>>
}