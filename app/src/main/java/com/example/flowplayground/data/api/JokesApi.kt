package com.example.flowplayground.data.api

import com.example.flowplayground.data.model.JokeResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesApi {

    @GET("jokes/random")
    suspend fun fetchRandomJoke(): JokeResponse

    @GET("jokes/ten")
    suspend fun fetchTenRandomJoke(): List<JokeResponse>

    @GET("jokes/{type}/random")
    suspend fun fetchRandomJokeOf(@Path("type") type: String): List<JokeResponse>

    @GET("jokes/{type}/ten")
    suspend fun fetchTenRandomJokeOf(@Path("type") type: String): List<JokeResponse>

    companion object {
        // https://github.com/15Dkatz/official_joke_api
        const val JokesBaseUrl = "https://official-joke-api.appspot.com/"
    }
}