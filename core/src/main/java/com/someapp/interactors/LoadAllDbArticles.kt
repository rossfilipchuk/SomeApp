package com.someapp.interactors

import com.someapp.data.ArticleRepository

class LoadAllDbArticles(private val articleRepository: ArticleRepository) {
    suspend operator fun invoke() = articleRepository.loadDb()
}