package com.epam.pockedox.view.fragments

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.epam.pockedox.R
import com.epam.pockedox.adapters.PokemonRecyclerViewAdapter
import com.epam.pockedox.domain.Pokemon
import com.epam.pockedox.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class PokemonListFragment : BaseFragment<MainViewModel>(R.layout.fragment_pokemon_list) {

    override val viewModel by viewModel<MainViewModel>()
    private val adapter = PokemonRecyclerViewAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPokemons.adapter = adapter
        setUpToolbar()
        observeData()
    }

    private fun setUpToolbar() {
        val toolbar: Toolbar? = activity?.findViewById(R.id.toolbar)
        toolbar?.navigationIcon = null
        toolbar?.background = ColorDrawable(resources.getColor(R.color.colorPrimary))
        toolbar?.title = resources.getString(R.string.app_name)
        val window = activity?.window
        window?.statusBarColor = resources.getColor(R.color.colorPrimary)
    }

    private fun observeData() {
        viewModel.loadPokemonList()

        viewModel.pokemonData.observe(viewLifecycleOwner, { data ->
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
