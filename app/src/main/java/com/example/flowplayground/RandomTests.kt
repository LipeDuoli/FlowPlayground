package com.example.flowplayground

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    flow {
        (1..4).forEach {
            emit(it)
            delay(150)
        }
    }
        .map { it * it }
        .filter { it % 2 == 0 }
        .collect {
            println(it)
        }
}