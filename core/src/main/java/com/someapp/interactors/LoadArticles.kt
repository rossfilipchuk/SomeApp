package com.someapp.interactors

import com.someapp.data.ArticleRepository

class LoadArticles(private val articleRepository: ArticleRepository) {
    suspend operator fun invoke() = articleRepository.load()
}