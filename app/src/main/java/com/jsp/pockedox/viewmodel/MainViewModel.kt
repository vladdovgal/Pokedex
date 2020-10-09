package com.jsp.pockedox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jsp.pockedox.data.PokemonRepositoryImpl
import com.jsp.pockedox.domain.Pokemon
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel() {

    private val repository = PokemonRepositoryImpl()

    private val _pokemonData = MutableLiveData<List<Pokemon>>()
    val pokemonData : LiveData<List<Pokemon>>
        get() = _pokemonData

    fun loadPokemonList() {
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                // imitate data loading
                delay(3_000)
                val response = repository.getPokemonList()
                _pokemonData.value = response
                errorMessageData.postValue(null)
            } catch (exception : Exception) {
                // Show exception to the user
                exception.printStackTrace()
                errorMessageData.postValue(exception.message)
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}