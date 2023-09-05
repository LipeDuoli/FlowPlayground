package com.example.flowplayground.ui.home.viewmodel.livedata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.flowplayground.data.repository.JokesRepository
import com.example.flowplayground.ui.home.viewmodel.BaseViewModel
import com.example.flowplayground.ui.model.Joke
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveDataFlowRepoViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : BaseViewModel() {

    private val _joke = MutableLiveData<Joke?>()
    val joke: LiveData<Joke?> = _joke

    init {
        fetchJoke()
    }

    fun fetchJoke() {
        // dangerous using this way
        viewModelScope.launch {
            jokesRepository.fetchRandomConstantFlowJoke().collect {
                Log.d(TAG, "LiveDataFlowRepoViewModel: Joke returned")
                _joke.postValue(it)
            }
        }
    }
}