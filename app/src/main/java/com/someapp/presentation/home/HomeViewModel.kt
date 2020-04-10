package com.someapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.someapp.data.ResourceState
import com.someapp.data.ArticleRepository
import com.someapp.domain.Article
import kotlinx.coroutines.*
import org.koin.java.KoinJavaComponent.inject

class HomeViewModel() : ViewModel() {

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val articleRepository: ArticleRepository by inject(ArticleRepository::class.java)

    private val _articles = MutableLiveData<ResourceState<List<Article>>>()

    val articles: LiveData<ResourceState<List<Article>>> = _articles

    fun loadNews() {
        _articles.value = ResourceState.loading()
        CoroutineScope(Dispatchers.IO).launch {
            _articles.postValue(articleRepository.load())
        }
    }

    fun loadSavedNews() {
        scope.launch {
            _articles.postValue(ResourceState.success(articleRepository.loadDb()))
        }
    }
}