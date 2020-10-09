package com.jsp.pockedox.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.jsp.pockedox.R
import com.jsp.pockedox.adapters.PokemonRecyclerViewAdapter
import com.jsp.pockedox.domain.Pokemon
import com.jsp.pockedox.viewmodel.MainViewModel
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