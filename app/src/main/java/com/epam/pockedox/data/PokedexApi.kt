package com.epam.pockedox.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokedexApi {
    fun createPokedexService() : PokedexService {

        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create()) // Json -> Kotlin dataclasses
            .build()

        return retrofit.create(PokedexService::class.java)
    }
}
