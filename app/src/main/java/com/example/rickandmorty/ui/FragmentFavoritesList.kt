package com.example.rickandmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.*
import com.example.rickandmorty.databinding.FragmentFavoritesListBinding
import com.example.rickandmorty.presentation.ViewModelCharacters
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFavoritesList : Fragment(R.layout.fragment_favorites_list), Adapter1.OnCharacterClickListener {

    private val adapter1 = Adapter1(this)

    private lateinit var binding: FragmentFavoritesListBinding

    private val viewModel: ViewModelCharacters by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoritesListBinding.bind(view)

        binding.RecyclerView1Favorites.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        favoritesObserver()

        viewModel.getFavoritesCharactersVM()

    }

    private fun favoritesObserver() {
        viewModel.favList.observe(requireActivity()){ characterEntityList ->
            binding.RecyclerView1Favorites.adapter = adapter1

            val list: List<Character1> = characterEntityList.map {
                Character1(
                    it.id.toInt(),
                    it.name,
                    it.status,
                    it.species,
                    it.gender,
                    it.image,
                    Origin(it.origin,""),
                    Location(it.location, "")

                )
            }
            adapter1.getData(AllCharacters(list))
        }
    }

    override fun onCharacterClick(character1: Character1, position: Int) {
        viewModel.sendDetails(character1)
        findNavController().navigate(R.id.action_fragmentFavoritesList_to_fragmentFavoritesDetails)
    }
}