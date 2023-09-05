package com.example.flowplayground.data.repository

import com.example.flowplayground.ui.model.Joke
import kotlinx.coroutines.flow.Flow

interface JokesRepository {

    suspend fun fetchRandomJoke(): Joke?

    suspend fun fetchRandomJokeOf(type: String): Joke?

    fun fetchRandomFlowJoke(): Flow<Joke>

    fun fetchRandomConstantFlowJoke(): Flow<Joke>

    fun fetchRandomFlowJokeOf(type: String): Flow<Joke>
}