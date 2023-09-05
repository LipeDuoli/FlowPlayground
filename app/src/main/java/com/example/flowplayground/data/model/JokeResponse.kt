package com.example.flowplayground.data.model

import com.example.flowplayground.ui.model.Joke
import kotlinx.serialization.Serializable

@Serializable
data class JokeResponse(
    val id: Int,
    val type: String,
    val setup: String,
    val punchline: String,
)

fun JokeResponse.toDomain() = Joke(
    id = id,
    type = type,
    setup = setup,
    punchline = punchline
)

fun List<JokeResponse>.toDomain() = map { it.toDomain() }
