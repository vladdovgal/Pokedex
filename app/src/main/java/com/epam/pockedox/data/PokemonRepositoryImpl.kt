package com.epam.pockedox.data

import com.epam.pockedox.domain.Pokemon
import com.epam.pockedox.domain.PokemonRepository

class PokemonRepositoryImpl : PokemonRepository {
    override fun getPokemonList(): List<Pokemon> {
        // todo loading from Room or RestApi
        return pokemonList
    }
}