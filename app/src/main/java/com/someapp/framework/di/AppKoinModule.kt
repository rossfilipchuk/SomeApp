package com.someapp.framework.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.someapp.data.ArticleDataSource
import com.someapp.data.ArticleRepository
import com.someapp.framework.ArticleDataSourceImpl
import com.someapp.framework.api.RetrofitService
import com.someapp.framework.db.AppDataBase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    single {
        provideRetrofitService(
            provideRetrofit(
                provideOkHttpClient(
                    provideLoggingInterceptor()
                )
            )
        )
    }

    single { AppDataBase.getInstance(androidContext()) }

    single<ArticleDataSource> { ArticleDataSourceImpl() }

    factory { ArticleRepository(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(RetrofitService.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun provideRetrofitService(retrofit: Retrofit): RetrofitService =
    retrofit.create(RetrofitService::class.java)