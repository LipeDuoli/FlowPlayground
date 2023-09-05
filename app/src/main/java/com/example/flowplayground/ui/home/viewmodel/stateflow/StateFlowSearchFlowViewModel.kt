package com.example.flowplayground.ui.home.viewmodel.stateflow

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.flowplayground.data.repository.JokesRepository
import com.example.flowplayground.ui.home.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StateFlowSearchFlowViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : BaseViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    val joke = query
        .debounce(2000)
        .distinctUntilChanged()
        .flatMapLatest {
            Log.d(TAG, "StateFlowSearchFlowViewModel: Joke returned")
            jokesRepository.fetchRandomFlowJokeOf(it)
        }.catch { exception ->
            Log.e(TAG, "StateFlowSearchFlowViewModel: Joke error")
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            null
        )

    fun search(q: String) {
        viewModelScope.launch {
            _query.emit(q)
        }
    }
}