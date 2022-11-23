package com.example.rickandmorty.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.model.CharacterEntity

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterEntity ORDER BY id")
    suspend fun getAllFavoritesCharacters(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterToFavorites(characterEntity: CharacterEntity)

    @Delete
    suspend fun deleteCharacterFromFavorites(characterEntity: CharacterEntity)

}