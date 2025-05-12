package com.example.kidex.model

data class Transformation(
    val name: String,
    val image: String,
    val ki: Int
)

data class Personaje(
    val id: Int,
    val name: String,
    val ki: String,
    val maxKi: String,
    val race: String,
    val gender: String,
    val description: String,
    val affiliation: String,
    val image: String,
    val transformations: List<transformacion>? = null
)
