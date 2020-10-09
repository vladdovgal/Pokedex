package com.jsp.pockedox.domain

interface PokemonRepository {
    fun getPokemonList() : List<Pokemon>
}