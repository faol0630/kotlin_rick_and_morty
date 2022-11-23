package com.example.rickandmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Character1
import com.example.rickandmorty.data.model.CharacterEntity
import com.example.rickandmorty.databinding.FragmentFavoritesDetailsBinding
import com.example.rickandmorty.presentation.ViewModelCharacters
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFavoritesDetails : Fragment(R.layout.fragment_favorites_details) {

    private lateinit var binding: FragmentFavoritesDetailsBinding

    private val viewModel: ViewModelCharacters by activityViewModels()

    private lateinit var character1: Character1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoritesDetailsBinding.bind(view)

        viewModel.itemDetails.observe(requireActivity()) {
            binding.favDetailsId.text = it.id.toString()
            binding.favDetailsName.text = it.name
            binding.favDetailsGender.text = it.gender
            binding.favDetailsSpecies.text = it.species
            binding.favDetailsStatus.text = it.status
            binding.favDetailsOrigin.text = it.origin.name
            binding.favDetailsLocation.text = it.location.name
            Glide.with(binding.favDetailsImageView)
                .asBitmap()
                .load(it.image)
                .transform(RoundedCorners(40))
                .into(BitmapImageViewTarget(binding.favDetailsImageView))

            character1 = Character1(
                it.id,
                it.name,
                it.status,
                it.species,
                it.gender,
                it.image,
                it.origin,
                it.location
            )
        }

        binding.btnDeleteFavoriteCharacter.setOnClickListener {

            viewModel.deleteCharacterFromFavorites(
                CharacterEntity(
                    character1.id.toString(),
                    character1.name,
                    character1.status,
                    character1.species,
                    character1.gender,
                    character1.image,
                    character1.origin.name,
                    character1.location.name
                )
            )
            Toast.makeText(requireContext(), "Character deleted from favorites", Toast.LENGTH_SHORT)
                .show()
        }

    }



}