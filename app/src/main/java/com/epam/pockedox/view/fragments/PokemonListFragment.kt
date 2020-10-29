package com.epam.pockedox.view.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.epam.pockedox.R
import com.epam.pockedox.adapters.PokemonRecyclerViewAdapter
import com.epam.pockedox.domain.Pokemon
import com.epam.pockedox.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class PokemonListFragment : BaseFragment<MainViewModel>(R.layout.fragment_pokemon_list) {

    override val viewModel by viewModel<MainViewModel>()
    private val adapter = PokemonRecyclerViewAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        loadPokemonList()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPokemons.adapter = adapter
        setUpToolbar()
        observeData()
        setUpPullToRefresh()
    }

    private fun setUpPullToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadPokemonList()
            if (viewModel.errorMessageData.value == null) {
                val snackBar = Snackbar.make(
                    swipeRefreshLayout,
                    resources.getString(R.string.data_updated),
                    Snackbar.LENGTH_LONG)
                snackBar.setAction("Dismiss") {
                    snackBar.dismiss()
                }
                snackBar.show()
            }
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpToolbar() {
        toolbar.apply {
            navigationIcon = null
            title = resources.getString(R.string.app_name)
        }
        val window = activity?.window
        window?.statusBarColor = resources.getColor(R.color.colorPrimary)
    }

    private fun observeData() {
        viewModel.pokemonData.observe(viewLifecycleOwner, { data ->
            setUpPokemonRecyclerView(data)
        })

        adapter.pokemonOnClickListener = object : PokemonRecyclerViewAdapter.PokemonItemOnClickListener {
            override fun onClicked(id: String) {
                val action = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(
                    id
                )
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

    private fun loadPokemonList() {
        viewModel.loadPokemonList()
    }
}
