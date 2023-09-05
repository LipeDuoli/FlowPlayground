package com.example.flowplayground.ui.home.viewmodel.livedata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.example.flowplayground.data.repository.JokesRepository
import com.example.flowplayground.ui.home.viewmodel.BaseViewModel
import com.example.flowplayground.ui.model.Joke
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LiveDataBuilderSearchViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : BaseViewModel() {

    private val _query = MutableLiveData("")
    val query: LiveData<String> = _query

    val joke: LiveData<Joke?> = query.switchMap { q ->
        liveData {
            jokesRepository.fetchRandomJokeOf(q)?.let {
                Log.d(TAG, "LiveDataBuilderSearchViewModel: Joke returned")
                emit(it)
            }
        }
    }

    fun search(q: String) {
        _query.value = q
    }
}