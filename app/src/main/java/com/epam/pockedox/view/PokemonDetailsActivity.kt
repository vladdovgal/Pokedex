package com.epam.pockedox.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import coil.api.load
import com.epam.pockedox.R
import com.epam.pockedox.domain.PokemonDetails
import com.epam.pockedox.viewmodel.PokemonDetailsViewModel
import kotlinx.android.synthetic.main.activity_pokemon_details.*

const val PARAM_POKEMON_ID = "param.pokemon.id"
class PokemonDetailsActivity : BaseActivity<PokemonDetailsViewModel>() {

    override val viewModel = PokemonDetailsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        observeData()
    }

    private fun observeData() {
        val pokemonId = intent.getStringExtra(PARAM_POKEMON_ID)
        if (pokemonId != null) {
            viewModel.loadPokemonData(pokemonId)
        }

        viewModel.pokemonDetailsLiveData.observe(this, Observer { pokemonDetails ->
            if (pokemonDetails != null) {
                showPokemonDetails(pokemonDetails)
            }
        })
    }

    private fun showPokemonDetails(pokemon : PokemonDetails) {
        name.text = pokemon.name
        image.load(pokemon.imageUrl)
    }

    companion object {
        fun openDetails(context: Context, id: String) {
            val intent = Intent(context, PokemonDetailsActivity::class.java)

            val bundle = Bundle()
            bundle.putString(PARAM_POKEMON_ID, id)
            intent.putExtras(bundle)

            context.startActivity(intent)
        }
    }
}