package com.example.flowplayground.ui.home.viewmodel.stateflow

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.flowplayground.data.repository.JokesRepository
import com.example.flowplayground.ui.home.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StateFlowDirectFlowReloadViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : BaseViewModel() {

    private val reload = MutableSharedFlow<Unit>()

    val joke = reload.flatMapLatest {
        jokesRepository.fetchRandomFlowJoke()
    }.map {// this map is only to show this log. Not needed
        Log.d(TAG, "StateFlowDirectFlowReloadViewModel: Joke returned")
        it
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    init {
        fetchJoke()
    }

    fun fetchJoke() {
        viewModelScope.launch {
            reload.emit(Unit)
        }
    }
}