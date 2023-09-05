package com.example.flowplayground.ui.home.viewmodel.stateflow

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.flowplayground.data.repository.JokesRepository
import com.example.flowplayground.ui.home.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StateFlowDirectFlowViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : BaseViewModel() {

    val joke = jokesRepository.fetchRandomConstantFlowJoke()
        .map {// this map is only to show this log. Not needed
            Log.d(TAG, "StateFlowDirectFlowViewModel: Joke returned")
            it
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )
}