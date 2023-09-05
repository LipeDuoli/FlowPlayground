package com.example.flowplayground.ui.home.viewmodel.stateflow

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.flowplayground.data.repository.JokesRepository
import com.example.flowplayground.ui.home.viewmodel.BaseViewModel
import com.example.flowplayground.ui.model.Joke
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StateFlowViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : BaseViewModel() {

    private val _joke: MutableStateFlow<Joke?> = MutableStateFlow(null)
    val joke = _joke.asStateFlow()

    init {
        fetchJoke()
    }

    fun fetchJoke() {
        viewModelScope.launch {
            jokesRepository.fetchRandomJoke().let {
                Log.d(TAG, "StateFlowViewModel: Joke returned")
                _joke.emit(it)
            }
        }
    }
}