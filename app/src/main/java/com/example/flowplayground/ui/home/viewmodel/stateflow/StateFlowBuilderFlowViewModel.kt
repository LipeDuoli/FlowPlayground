package com.example.flowplayground.ui.home.viewmodel.stateflow

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.flowplayground.data.repository.JokesRepository
import com.example.flowplayground.ui.home.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class StateFlowBuilderFlowViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : BaseViewModel() {

    val joke = flow {
        jokesRepository.fetchRandomConstantFlowJoke().collect {
            Log.d(TAG, "StateFlowBuilderFlowViewModel: Joke returned")
            emit(it)
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
}