package dev.kelompokceria.movieapi

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {

    @GET("/api/pokemon")
    suspend fun getPokemonList(): List<Pokemon>


}