package com.epam.pockedox.data

import com.epam.pockedox.domain.PokemonInfoResponse
import com.epam.pockedox.domain.PokemonListResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

fun createPokedexService() : PokedexService {

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create()) // Json -> Kotlin dataclasses
        .build()

    return retrofit.create(PokedexService::class.java)
}
