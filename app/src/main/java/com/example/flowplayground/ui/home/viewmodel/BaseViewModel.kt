package com.example.flowplayground.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {

    override fun onCleared() {
        Log.d(TAG, "${this.javaClass.simpleName}: Cleared")
        super.onCleared()
    }

    companion object {
        const val TAG = "FlowViewModel"
    }
}