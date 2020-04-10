package com.someapp.interactors

import com.someapp.data.ArticleRepository
import com.someapp.domain.Article

class DeleteArticle(private val articleRepository: ArticleRepository) {
    suspend operator fun invoke(article: Article) = articleRepository.deleteArticle(article)
}