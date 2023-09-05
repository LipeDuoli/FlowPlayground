@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.example.flowplayground.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowplayground.data.repository.JokesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : ViewModel() {

//    private val _joke: MutableStateFlow<Joke?> = MutableStateFlow(null)
//    val joke = _joke.asStateFlow()

    val search = MutableStateFlow("")
    private val reload = MutableSharedFlow<Unit>()

//    val joke = combine(search, reload) { query, _ ->
//        query
//    }
//        .debounce(2000)
//        .distinctUntilChanged()
//        .flatMapLatest {
//            jokesRepository.fetchRandomJokeOf(it)
//        }.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = null
//        )

//    val joke = reload
//        .flatMapLatest {
//            jokesRepository.fetchRandomJoke()
//        }
//        .flowOn(Dispatchers.IO)
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = null
//        )

//    val joke = jokesRepository.fetchRandomJoke()
//        .flowOn(Dispatchers.IO)
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = null
//        )

    val joke = search
        .debounce(2000)
        .distinctUntilChanged()
        .flatMapLatest {
            jokesRepository.fetchRandomFlowJokeOf(it)
        }.catch { exception ->
            Log.e("TAG", "error fetch: ", exception)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            null
        )

//    val joke = flow {
//        while (true){
//            delay(5000)
//            jokesRepository.fetchRandomJoke().collect {
//                Log.d("TAG", "fetch: $it")
//                emit(it)
//            }
//        }
//    }.stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = null
//        )

    init {
//        fetchJoke()
    }

    fun fetchJoke() {
//        viewModelScope.launch {
//                jokesRepository.fetchRandomJoke().collect {
//                    _joke.emit(it)
//                }
//        }

        viewModelScope.launch {
            reload.emit(Unit)
        }
    }

    fun updateSearch(s: String) {
        viewModelScope.launch {
            search.emit(s)
        }
    }
}