package com.example.rickandmorty.repository

import com.example.rickandmorty.data.model.AllCharacters
import com.example.rickandmorty.data.model.CharacterEntity

interface RepositoryInt {

    suspend fun getAllCharacters(page: Int): AllCharacters

    //room-------

    suspend fun insertCharacterToFavorites(characterEntity: CharacterEntity)

    suspend fun deleteCharacterFromFavorites(characterEntity: CharacterEntity)

    suspend fun getFavoritesCharacters(): List<CharacterEntity>

}