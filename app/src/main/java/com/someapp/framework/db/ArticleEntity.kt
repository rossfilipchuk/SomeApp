package com.someapp.framework.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.someapp.domain.Article

@Entity(tableName = "articles")
data class ArticleEntity(
    //For this News API only title is always available
    @PrimaryKey() val articleTitle: String,
    @Embedded val article: Article
)