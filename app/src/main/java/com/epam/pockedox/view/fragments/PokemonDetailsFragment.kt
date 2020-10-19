package com.epam.pockedox.view.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.epam.pockedox.R
import com.epam.pockedox.domain.PokemonDetails
import com.epam.pockedox.viewmodel.PokemonDetailsViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_details.*

class PokemonDetailsFragment : BaseFragment<PokemonDetailsViewModel>(R.layout.fragment_pokemon_details) {

    override val viewModel = PokemonDetailsViewModel()
    private val navArgs by navArgs<PokemonDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    private fun observeData() {
        val pokemonId = navArgs.id
        viewModel.loadPokemonData(pokemonId)

        viewModel.pokemonDetailsLiveData.observe(viewLifecycleOwner, Observer { pokemonDetails ->
            if (pokemonDetails != null) {
                showPokemonDetails(pokemonDetails)
            }
        })
    }

    private fun showPokemonDetails(pokemon : PokemonDetails) {
        name.text = pokemon.name
        image.load(pokemon.imageUrl)
    }
}
