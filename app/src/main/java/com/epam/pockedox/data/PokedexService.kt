package com.epam.pockedox.data

import com.epam.pockedox.domain.PokemonInfoResponse
import com.epam.pockedox.domain.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {
    @GET("pokemon")
    fun getPokemonList(
        @Query("limit") limit : Int = 30,
        @Query("offset") offset : Int = 5
    ) : Call<PokemonListResponse>

    @GET("pokemon/{name}")
    fun getPokemonById(@Path("name") name: String): Call<PokemonInfoResponse>
}
