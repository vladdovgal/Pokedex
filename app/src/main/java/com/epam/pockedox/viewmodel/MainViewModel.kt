package com.epam.pockedox.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.epam.pockedox.data.PokemonRepository
import com.epam.pockedox.domain.Pokemon
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class MainViewModel : BaseViewModel() {

    private val repository by KoinJavaComponent.inject(PokemonRepository::class.java)

    private val _pokemonData = MutableLiveData<List<Pokemon>>()
    val pokemonData : LiveData<List<Pokemon>>
        get() = _pokemonData

    fun loadPokemonList() {
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                // imitate data loading
                delay(1_000)
                val response = repository.getPokemonList()
                // getting error with probability 0.1
                if((1..10).random() == 1) {
                    errorMessageData.postValue("Something went wrong. " +
                            "\n Try restarting application")
                } else {
                    _pokemonData.value = response
                    errorMessageData.postValue(null)
                }
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