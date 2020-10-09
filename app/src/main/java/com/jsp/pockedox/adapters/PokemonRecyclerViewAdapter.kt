package com.jsp.pockedox.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jsp.pockedox.R
import com.jsp.pockedox.databinding.ListItemPokemonBinding
import com.jsp.pockedox.domain.Pokemon

class PokemonRecyclerViewAdapter : BaseRecyclerAdapter<Pokemon, PokemonRecyclerViewAdapter.PokemonViewHolder>() {

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
    }

    inner class PokemonViewHolder(val listItemRecyclerViewPokemonBinding : ListItemPokemonBinding) :
            RecyclerView.ViewHolder(listItemRecyclerViewPokemonBinding.root) {
        // todo onclick listener
    }
}