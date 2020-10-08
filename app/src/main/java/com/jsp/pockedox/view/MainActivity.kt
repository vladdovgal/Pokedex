package com.jsp.pockedox.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jsp.pockedox.R
import com.jsp.pockedox.adapters.PokemonRecyclerViewAdapter
import com.jsp.pockedox.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
    }

    /**
     * Sets up recycler view with list of pokemons
     */
    private fun setUpRecyclerView() {
        rvPokemons.adapter = PokemonRecyclerViewAdapter().also { viewModel.pokemons.value?.let { _ ->
            it.addAll(
                viewModel.pokemons.value!!
            )
        } }
    }
}