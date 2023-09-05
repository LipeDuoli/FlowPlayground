package com.example.flowplayground.ui.home.viewmodel.livedata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.flowplayground.data.repository.JokesRepository
import com.example.flowplayground.ui.home.viewmodel.BaseViewModel
import com.example.flowplayground.ui.model.Joke
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LiveDataBuilderFlowViewModel @Inject constructor(
    private val jokesRepository: JokesRepository
) : BaseViewModel() {

    val joke: LiveData<Joke?> = liveData {
        jokesRepository.fetchRandomConstantFlowJoke().collect {
            Log.d(TAG, "LiveDataBuilderFlowViewModel: Joke returned")
            emit(it)
        }
    }
}