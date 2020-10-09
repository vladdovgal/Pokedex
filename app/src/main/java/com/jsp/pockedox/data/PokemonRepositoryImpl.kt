package com.jsp.pockedox.data

import com.jsp.pockedox.domain.Pokemon
import com.jsp.pockedox.domain.PokemonRepository

class PokemonRepositoryImpl : PokemonRepository {
    override fun getPokemonList(): List<Pokemon> {
        // todo loading from room database or RestApi
        return pokemonList
    }
}