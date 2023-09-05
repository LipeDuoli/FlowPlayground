package com.example.flowplayground.data.di

import com.example.flowplayground.data.api.JokesApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(log: HttpLoggingInterceptor) =
        OkHttpClient.Builder().addInterceptor(log).build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(JokesApi.JokesBaseUrl)
        .client(client)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Singleton
    @Provides
    fun provideJokeApi(retrofit: Retrofit) = retrofit.create(JokesApi::class.java)
}