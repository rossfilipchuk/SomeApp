package com.someapp.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.someapp.data.ArticleRepository
import com.someapp.domain.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class ArticleDetailsViewModel() : ViewModel() {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val articleRepository: ArticleRepository by KoinJavaComponent.inject(
        ArticleRepository::class.java
    )


    private val _article = MutableLiveData<Article>()
    private val _isArticleSaved = MutableLiveData<Boolean>()

    val article: LiveData<Article> = _article
    val isArticleSaved: LiveData<Boolean> = _isArticleSaved

    fun setArticle(article: Article) {
        _article.value = article
        checkIsArticleSaved()
    }

    fun saveArticle() {
        scope.launch {
            _article.value?.let {
                articleRepository.saveArticle(it)
                _isArticleSaved.postValue(true)
            }
        }
    }

    fun deleteArticle() {
        scope.launch {
            _article.value?.let {
                articleRepository.deleteArticle(it)
                _isArticleSaved.postValue(false)
            }
        }
    }

    fun loadArticleFromDbByTitle(articleTitle: String) {
        scope.launch {
            articleRepository.loadDbByTitle(articleTitle)?.let { article ->
                _article.postValue(article)
                _isArticleSaved.postValue(true)
            } ?: _isArticleSaved.postValue(false)
        }
    }

    private fun checkIsArticleSaved() {
        scope.launch {
            _article.value?.let {
                articleRepository.loadDbByTitle(it.title)?.let {
                    _isArticleSaved.postValue(true)
                } ?: _isArticleSaved.postValue(false)
            }
        }
    }
}