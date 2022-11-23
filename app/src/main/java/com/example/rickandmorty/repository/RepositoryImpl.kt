package com.example.rickandmorty.repository

import com.example.rickandmorty.data.local.AppDatabase
import com.example.rickandmorty.data.model.AllCharacters
import com.example.rickandmorty.data.model.CharacterEntity
import com.example.rickandmorty.data.remote.CharactersService
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val charactersService: CharactersService,
    private val appDatabase: AppDatabase
) : RepositoryInt {

    override suspend fun getAllCharacters(page: Int): AllCharacters{
        return charactersService.getAllCharacters(page)
    }

    //room-------

    override suspend fun insertCharacterToFavorites(characterEntity: CharacterEntity) {
        appDatabase.characterDao().insertCharacterToFavorites(characterEntity)
    }

    override suspend fun deleteCharacterFromFavorites(characterEntity: CharacterEntity) {
        appDatabase.characterDao().deleteCharacterFromFavorites(characterEntity)
    }

    override suspend fun getFavoritesCharacters(): List<CharacterEntity> {
        return appDatabase.characterDao().getAllFavoritesCharacters()
    }

}