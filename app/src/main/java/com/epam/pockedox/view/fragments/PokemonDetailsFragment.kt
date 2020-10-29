package com.epam.pockedox.view.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import coil.api.load
import com.epam.pockedox.R
import com.epam.pockedox.domain.PokemonDetails
import com.epam.pockedox.viewmodel.PokemonDetailsViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_details.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Class which represents Fragment which displays details about pokemon
 */
class PokemonDetailsFragment : BaseFragment<PokemonDetailsViewModel>(R.layout.fragment_pokemon_details) {

    override val viewModel by viewModel<PokemonDetailsViewModel>()
    private val navArgs by navArgs<PokemonDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpToolbar()
        observeData()
    }

    private fun setUpToolbar() {
        toolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
            background = ColorDrawable(resources.getColor(R.color.dragon))
            title = ""
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
        val window = activity?.window
        window?.statusBarColor = resources.getColor(R.color.dragon)
    }

    private fun observeData() {
        val pokemonId = navArgs.id
        viewModel.loadPokemonData(pokemonId)

        viewModel.pokemonDetailsLiveData.observe(viewLifecycleOwner, Observer { pokemonDetails ->
            if (pokemonDetails != null) {
                loadPokemonDataInViews(pokemonDetails)
            }
        })
    }

    private fun loadPokemonDataInViews(pokemon: PokemonDetails) {
        tvName.text = pokemon.name
        image.load(pokemon.imageUrl)

        tvWeight.text = pokemon.getWeightString()
        tvHeights.text = pokemon.getHeightString()

        progress_hp.labelText = pokemon.getHpString()
        progress_hp.max = PokemonDetails.maxHp.toFloat()
        progress_hp.progress = pokemon.hp.toFloat()

        progress_attack.labelText = pokemon.getAttackString()
        progress_attack.max = PokemonDetails.maxAttack.toFloat()
        progress_attack.progress = pokemon.attack.toFloat()

        progress_defence.labelText = pokemon.getDefenseString()
        progress_defence.max = PokemonDetails.maxDefense.toFloat()
        progress_defence.progress = pokemon.defense.toFloat()

        progress_speed.labelText = pokemon.getSpeedString()
        progress_speed.max = PokemonDetails.maxSpeed.toFloat()
        progress_speed.progress = pokemon.speed.toFloat()

        progress_experience.labelText = pokemon.getExpString()
        progress_experience.max = PokemonDetails.maxExp.toFloat()
        progress_experience.progress = pokemon.exp.toFloat()
    }
}
