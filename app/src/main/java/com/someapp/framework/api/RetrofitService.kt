package com.someapp.framework.api

import com.someapp.domain.News
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    @GET("https://newsapi.org/v2/top-headlines?q=World&sortBy=publishedAt&apiKey=058d077675604ecc84ab0b2f4c05fb12")
    fun loadTopHeadlinesAsync(): Deferred<Response<News>>
}