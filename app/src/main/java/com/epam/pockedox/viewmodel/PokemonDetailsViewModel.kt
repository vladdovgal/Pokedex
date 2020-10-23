package com.epam.pockedox.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.epam.pockedox.data.PokemonRepository
import com.epam.pockedox.domain.PokemonDetails
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent

class PokemonDetailsViewModel : BaseViewModel() {

    private val repository by KoinJavaComponent.inject(PokemonRepository::class.java)

    private val _pokemonDetailsLiveData = MutableLiveData<PokemonDetails>()
    val pokemonDetailsLiveData: LiveData<PokemonDetails> = _pokemonDetailsLiveData


    fun loadPokemonData(pokemonId: String) {
        viewModelScope.launch {
            try {
                isLoading.postValue(true)
                delay(1_000)
                val response = repository.getPokemonById(pokemonId)
                _pokemonDetailsLiveData.postValue(response)
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