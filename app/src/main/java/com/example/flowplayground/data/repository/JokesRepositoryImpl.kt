package com.example.flowplayground.data.repository

import android.util.Log
import com.example.flowplayground.data.api.JokesApi
import com.example.flowplayground.data.model.toDomain
import com.example.flowplayground.ui.model.Joke
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val jokesApi: JokesApi
) : JokesRepository {

    override suspend fun fetchRandomJoke(): Joke? {
        return try {
            val joke = jokesApi.fetchRandomJoke().toDomain()
            Log.d(TAG, "Joke fetched")
            joke
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.e(TAG, "Joke Exception")
            null
        }
    }

    override suspend fun fetchRandomJokeOf(type: String): Joke? {
        return try {
            val joke = jokesApi.fetchRandomJokeOf(type).toDomain().firstOrNull()
            Log.d(TAG, "Joke fetched")
            joke
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            Log.e(TAG, "Joke Exception")
            null
        }
    }

    override fun fetchRandomFlowJoke(): Flow<Joke> = flow {
        val joke = jokesApi.fetchRandomJoke().toDomain()
        Log.d(TAG, "Joke fetched")
        emit(joke)
    }.catch {
        Log.e(TAG, "Joke Exception")
    }

    override fun fetchRandomConstantFlowJoke(): Flow<Joke> = flow {
        while (true) {
            val joke = jokesApi.fetchRandomJoke().toDomain()
            Log.d(TAG, "Joke fetched")
            emit(joke)
            delay(5_000)
        }
    }.catch {
        Log.e(TAG, "Joke Exception")
    }

    override fun fetchRandomFlowJokeOf(type: String): Flow<Joke> = flow {
        jokesApi.fetchRandomJokeOf(type).toDomain().firstOrNull()?.let {
            Log.d(TAG, "Joke fetched")
            emit(it)
        }
    }.catch {
        Log.e(TAG, "Joke Exception")
    }

    companion object {
        private const val TAG = "FlowRepository"
    }
}

@Module
@InstallIn(ViewModelComponent::class)
abstract class JokeRepositoryModule {

    @Binds
    abstract fun bindJokeRepository(repository: JokesRepositoryImpl): JokesRepository
}