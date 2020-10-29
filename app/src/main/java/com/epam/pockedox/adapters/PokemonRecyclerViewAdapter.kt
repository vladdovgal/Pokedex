package com.epam.pockedox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.epam.pockedox.R
import com.epam.pockedox.databinding.ListItemPokemonBinding
import com.epam.pockedox.domain.Pokemon

class PokemonRecyclerViewAdapter : BaseRecyclerAdapter<Pokemon, PokemonRecyclerViewAdapter.PokemonViewHolder>() {

    var pokemonOnClickListener: PokemonItemOnClickListener? = null

    interface PokemonItemOnClickListener {
        fun onClicked(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder =
        PokemonViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_pokemon,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.listItemRecyclerViewPokemonBinding.pokemon = items[position]
        holder.bindTo(items[position], pokemonOnClickListener)
    }

    inner class PokemonViewHolder(val listItemRecyclerViewPokemonBinding : ListItemPokemonBinding) :
            RecyclerView.ViewHolder(listItemRecyclerViewPokemonBinding.root) {

        fun bindTo(pokemon: Pokemon, pokemonItemOnClickListener: PokemonItemOnClickListener?) {
            itemView.setOnClickListener {
                pokemonItemOnClickListener?.onClicked(pokemon.id)
            }
        }
    }
}
