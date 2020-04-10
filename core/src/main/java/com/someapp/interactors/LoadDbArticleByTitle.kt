package com.someapp.interactors

import com.someapp.data.ArticleRepository

class LoadDbArticleByTitle(private val articleRepository: ArticleRepository) {
    suspend operator fun invoke(title: String) = articleRepository.loadDbByTitle(title)
}