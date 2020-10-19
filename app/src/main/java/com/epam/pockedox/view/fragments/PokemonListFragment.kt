package com.epam.pockedox.view.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.epam.pockedox.R
import com.epam.pockedox.adapters.PokemonRecyclerViewAdapter
import com.epam.pockedox.domain.Pokemon
import com.epam.pockedox.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

class PokemonListFragment : BaseFragment<MainViewModel>(R.layout.fragment_pokemon_list) {
    override val viewModel = MainViewModel()
    private val adapter = PokemonRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        viewModel.loadPokemonList()

        viewModel.pokemonData.observe(viewLifecycleOwner, Observer { data ->
            setUpPokemonRecyclerView(data)
        })

        adapter.pokemonOnClickListener = object : PokemonRecyclerViewAdapter.PokemonItemOnClickListener {
            override fun onClicked(id: String) {
                val action = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(id)
                findNavController().navigate(action)
            }
        }
    }

    /**
     * Sets up recycler view with list of pokemon
     */
    private fun setUpPokemonRecyclerView(data: List<Pokemon>) {
        rvPokemons.also { recyclerView ->
            recyclerView.adapter = adapter.also {
                it.addAll(data)
            }
        }
    }
}
