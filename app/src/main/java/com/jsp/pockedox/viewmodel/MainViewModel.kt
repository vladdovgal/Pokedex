package com.jsp.pockedox.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jsp.pockedox.model.Pokemon
import com.jsp.pockedox.repository.pokemonList

class MainViewModel : ViewModel() {
    var pokemons = MutableLiveData<List<Pokemon>>()

    init {
        pokemons.value = pokemonList
    }
}