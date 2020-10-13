package com.epam.pockedox.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokedexService {
    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit : Int = 30,
        @Query("offset") offset : Int = 0
    ) : Call<PokemonListResponse>
}