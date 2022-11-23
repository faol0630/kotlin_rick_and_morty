package com.example.rickandmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.rickandmorty.R
import com.example.rickandmorty.data.model.Character1
import com.example.rickandmorty.data.model.CharacterEntity
import com.example.rickandmorty.databinding.FragmentDetailsBinding
import com.example.rickandmorty.presentation.ViewModelCharacters
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentDetails : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding

    private val viewModel: ViewModelCharacters by activityViewModels()

    private lateinit var character1: Character1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailsBinding.bind(view)

        viewModel.itemDetails.observe(requireActivity()) {

            binding.detailsId.text = it.id.toString()
            binding.detailsGender.text = it.gender
            binding.detailsName.text = it.name
            binding.detailsSpecies.text = it.species
            binding.detailsstatus.text = it.status
            binding.detailsOrigin.text = it.origin.name
            binding.detailsLocation.text = it.location.name

            Glide.with(requireContext())
                .load(it.image)
                .transform(RoundedCorners(40))
                .into(binding.detailsImageView)

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

        binding.btnSaveInFavorites.setOnClickListener {

            val originName = binding.detailsOrigin.text.toString()
            val locationName = binding.detailsLocation.text.toString()

            viewModel.insertCharacterToFavorites(
                CharacterEntity(
                    character1.id.toString(),
                    character1.name,
                    character1.status,
                    character1.species,
                    character1.gender,
                    character1.image,
                    originName,
                    locationName
                )
            )
            Toast.makeText(requireContext(), "Character added to favorites", Toast.LENGTH_LONG)
                .show()
        }

    }
}