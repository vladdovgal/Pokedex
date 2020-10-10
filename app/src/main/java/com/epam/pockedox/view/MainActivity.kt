package com.epam.pockedox.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.epam.pockedox.R
import com.epam.pockedox.adapters.PokemonRecyclerViewAdapter
import com.epam.pockedox.domain.Pokemon
import com.epam.pockedox.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    override val viewModel = MainViewModel()
    private val adapter = PokemonRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeData()
    }

    private fun observeData() {
        viewModel.loadPokemonList()

        viewModel.pokemonData.observe(this, Observer { data ->
            setUpPokemonRecyclerView(data)
        })
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