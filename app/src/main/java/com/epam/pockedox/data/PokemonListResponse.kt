package com.epam.pockedox.data

import androidx.core.net.toUri

data class PokemonListResponse(val count : Int, val results : List<PokemonPartialInfo>)

data class PokemonPartialInfo(val name : String, val url : String)

val PokemonPartialInfo.id: String
    get() = url.toUri().lastPathSegment ?: ""

val PokemonPartialInfo.imageUrl: String
    get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"