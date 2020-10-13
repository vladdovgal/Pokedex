package com.epam.pockedox.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit : Int = 20,
        @Query("offset") offset : Int = 0
    )

    @GET("pokemon/{name}")
    suspend fun fetchPokemonInfo(@Path("name") name: String)
}