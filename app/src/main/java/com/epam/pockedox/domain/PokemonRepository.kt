package com.epam.pockedox.domain

interface PokemonRepository {
    fun getPokemonList() : List<Pokemon>
}