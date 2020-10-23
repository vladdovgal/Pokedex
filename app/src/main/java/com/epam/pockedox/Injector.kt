package com.epam.pockedox

import com.epam.pockedox.data.PokemonRepository
import com.epam.pockedox.data.createPokedexService
import com.epam.pockedox.viewmodel.MainViewModel
import com.epam.pockedox.viewmodel.PokemonDetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { MainViewModel() }
    viewModel { PokemonDetailsViewModel() }
}

val repositoryModule = module {
    single { PokemonRepository(createPokedexService()) }
}
