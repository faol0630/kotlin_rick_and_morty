package com.example.rickandmorty.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.rickandmorty.data.model.AllCharacters
import com.example.rickandmorty.data.model.Character1
import com.example.rickandmorty.databinding.ItemCharacterBinding

class Adapter1(
    private val itemClickListener: OnCharacterClickListener,
) : RecyclerView.Adapter<Adapter1.ViewHolder1>() {

    interface OnCharacterClickListener{
        fun onCharacterClick(character1: Character1, position: Int)
    }
    private var listCharacters : List<Character1> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder1 {

        val itemBinding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        val holder = ViewHolder1(itemBinding.itemView)
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != RecyclerView.NO_POSITION
            } ?: return@setOnClickListener
            itemClickListener.onCharacterClick(listCharacters[position], position)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder1, position: Int) {
       holder.insideViewHolder(listCharacters[position])
    }

    override fun getItemCount() = listCharacters.size

    fun getData(characters: AllCharacters){
        this.listCharacters = characters.results
    }

    inner class ViewHolder1(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun insideViewHolder(character: Character1){
            val itemViewHolder = ItemCharacterBinding.bind(itemView)
            itemViewHolder.apply {
                itemName.text = character.name
                Glide.with(itemImageView)
                    .load(character.image)
                    .transform(RoundedCorners(40))
                    .into(itemImageView)
            }
        }

    }

}