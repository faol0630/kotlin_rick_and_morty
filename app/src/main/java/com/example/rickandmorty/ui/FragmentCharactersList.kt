package com.example.rickandmorty.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmorty.R
import com.example.rickandmorty.core.SealedClass1
import com.example.rickandmorty.core.hide
import com.example.rickandmorty.core.show
import com.example.rickandmorty.data.model.Character1
import com.example.rickandmorty.databinding.FragmentCharactersListBinding
import com.example.rickandmorty.presentation.ViewModelCharacters
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentCharactersList : Fragment(R.layout.fragment_characters_list),
    Adapter1.OnCharacterClickListener {

    private val adapter1 = Adapter1(this)

    private lateinit var binding: FragmentCharactersListBinding

    private val viewModel: ViewModelCharacters by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCharactersListBinding.bind(view)

        binding.rvCharacters.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvCharacters.adapter = adapter1

        viewModel.allCharactersVM.observe(requireActivity()) {
            updatingSealedClass(it)
        }

        var pageNumber = 1

        viewModel.getAllCharactersVM(pageNumber)

        binding.btnGoToPreviousPage.setOnClickListener {

            binding.rvCharacters.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvCharacters.adapter = adapter1

            viewModel.allCharactersVM.observe(requireActivity()) {
                updatingSealedClass(it)
            }

            if (pageNumber > 1) {
                pageNumber--
            }
            viewModel.getAllCharactersVM(pageNumber)

        }

        binding.btnGoToPageOne.setOnClickListener {

            binding.rvCharacters.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvCharacters.adapter = adapter1

            viewModel.allCharactersVM.observe(requireActivity()) {
                updatingSealedClass(it)
            }
            viewModel.getAllCharactersVM(1)
            pageNumber = 1

        }

        binding.btnGoToNextPage.setOnClickListener {

            binding.rvCharacters.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvCharacters.adapter = adapter1

            viewModel.allCharactersVM.observe(requireActivity()) {
                updatingSealedClass(it)
            }

            if (pageNumber < 42) {
                pageNumber++
            }
            viewModel.getAllCharactersVM(pageNumber)

        }

        binding.btnGoToFavorites.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentCharactersList_to_fragmentFavoritesList)
        }

    }

    private fun updatingSealedClass(sealedClass1: SealedClass1) {

        when (sealedClass1) {
            SealedClass1.Loading -> {
                binding.rvCharacters.hide()
                binding.errorImageView.hide()
                binding.progressBarCharacters.show()
            }
            SealedClass1.Error -> {
                binding.errorImageView.show()
                binding.rvCharacters.hide()
                binding.progressBarCharacters.hide()
            }
            is SealedClass1.Content -> {
                binding.rvCharacters.show()
                binding.errorImageView.hide()
                binding.progressBarCharacters.hide()
                adapter1.getData(sealedClass1.sealedList)
            }
        }
    }

    override fun onCharacterClick(character1: Character1, position: Int) {
        viewModel.sendDetails(character1)
        findNavController().navigate(R.id.action_fragmentCharactersList_to_fragmentDetails)
    }
}