package com.someapp.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "title") var title: String = "",
    @Json(name = "author") var author: String? = "",
    @Json(name = "description") var description: String? = "",
    @Json(name = "url") var url: String? = "",
    @Json(name = "urlToImage") var urlToImage: String? = "",
    @Json(name = "publishedAt") var publishedAt: String? = "",
    @Json(name = "content") var content: String? = ""
) : Serializable