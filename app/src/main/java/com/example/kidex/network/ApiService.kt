package com.example.kidex.network

import com.example.kidex.model.Personaje
import com.example.kidex.model.PersonajeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("characters")
    suspend fun getPersonajes(
        @Query("limit") limit: Int = 58
    ): PersonajeResponse

    @GET("characters/{id}")
    suspend fun getPersonajeDetalle(
        @Path("id") id: Int
    ): Personaje

}


