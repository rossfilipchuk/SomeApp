package com.someapp.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class News(
    @Json(name = "status") val status: String = "none",
    @Json(name = "totalResults") val totalResults: Int = 0,
    @Json(name = "articles") val articles: List<Article> = listOf()
)


