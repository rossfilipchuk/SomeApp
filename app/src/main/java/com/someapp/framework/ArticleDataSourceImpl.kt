package com.someapp.framework

import com.someapp.data.ResourceState
import com.someapp.data.ArticleDataSource
import com.someapp.domain.Article
import com.someapp.framework.api.RetrofitService
import com.someapp.framework.db.AppDataBase
import com.someapp.framework.db.ArticleEntity
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject

class ArticleDataSourceImpl : ArticleDataSource {
    private val articleDataBase: AppDataBase by inject(AppDataBase::class.java)
    private val articleDao = articleDataBase.articleDao()

    private val retrofitService: RetrofitService by inject(RetrofitService::class.java)

    override suspend fun loadDbByTitle(title: String): Article? {
        return articleDao.loadByTitle(title)
    }

    override suspend fun loadDb(): List<Article> {
        return articleDao.loadAll()
    }

    override suspend fun save(article: Article) {
        articleDao.save(ArticleEntity(articleTitle = article.title, article = article))
    }

    override suspend fun delete(article: Article) {
        articleDao.delete(ArticleEntity(articleTitle = article.title, article = article))
    }

    override suspend fun load(): ResourceState<List<Article>> {
         val request = retrofitService.loadTopHeadlinesAsync()

        val response = request.await()
        if (response.isSuccessful) {
            response.body()?.let {
                return ResourceState.success(it.articles)
            } ?: run { return ResourceState.error() }
        } else {
            return ResourceState.error()
        }
    }
}