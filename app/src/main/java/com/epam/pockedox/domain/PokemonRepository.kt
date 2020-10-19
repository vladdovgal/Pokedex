package com.epam.pockedox.domain

import android.util.Log
import com.epam.pockedox.data.PokedexApi
import com.epam.pockedox.data.PokedexService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class PokemonRepository {

    private val api : PokedexService = PokedexApi.createPokedexService()

    suspend fun getPokemonList(): List<Pokemon> {
        return suspendCoroutine { continuation ->
            api.getPokemonList().enqueue(object : Callback<PokemonListResponse> {
                override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>
                ) {
                    if (response.isSuccessful) {
                        val pokemonListResponse : PokemonListResponse? = response.body()
                        val pokemonList = pokemonListResponse?.results?.map { pokemonPartialInfoDto ->
                            Pokemon(pokemonPartialInfoDto.name, pokemonPartialInfoDto.name, pokemonPartialInfoDto.imageUrl)
                        }
                        continuation.resume(pokemonList!!)
                    } else {
                        // server response with error
                        continuation.resumeWithException(Exception("Server responses with error"))
                        Log.e("myLogs", "PokemonRepository : Response in not successful")
                    }
                }

                override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                    Log.e("myLogs", "PokemonRepository : Response failure")
                }
            })
        }
    }

    suspend fun getPokemonById(id : String) : PokemonDetails {
        return suspendCoroutine { continuation ->
            api.getPokemonById(id).enqueue(object : Callback<PokemonInfoResponse> {
                override fun onResponse(
                    call: Call<PokemonInfoResponse>,
                    response: Response<PokemonInfoResponse>
                ) {
                    val pokemonInfo = response.body()
                    if (response.isSuccessful && pokemonInfo != null) {
                        val abilities = pokemonInfo.abilities.map { it.ability.name }
                        val pokemon = PokemonDetails(pokemonInfo.id, pokemonInfo.name, pokemonInfo.imageUrl,abilities)
                        continuation.resume(pokemon)
                    } else {
                        // server response with error
                        continuation.resumeWithException(Exception("Server responses with error"))
                        Log.e("myLogs", "PokemonRepository : Response in not successful")
                    }
                }

                override fun onFailure(call: Call<PokemonInfoResponse>, t: Throwable) {
                    Log.e("myLogs", "PokemonRepository : Response failure")
                }
            })
        }
    }
}
