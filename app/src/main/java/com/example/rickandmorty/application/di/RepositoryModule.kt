package com.example.rickandmorty.application.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.data.local.AppDatabase
import com.example.rickandmorty.data.local.CharacterDao
import com.example.rickandmorty.data.remote.CharactersAPI
import com.example.rickandmorty.data.remote.CharactersService
import com.example.rickandmorty.repository.RepositoryImpl
import com.example.rickandmorty.repository.RepositoryInt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {


    @Provides
    fun providesCharactersService(): CharactersService = CharactersAPI().getCharacters()


    @Provides
    fun providesCharacterRepositoryImpl(
        character: CharactersService,
        appDatabase: AppDatabase
    ): RepositoryImpl = RepositoryImpl(character, appDatabase)


    @Provides
    fun providesRepositoryInterface(
        repositoryImpl: RepositoryImpl
    ) : RepositoryInt = repositoryImpl

    //Room----------------------------------------


    @Provides
    fun providesAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "characters_database")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun providesCharacterDao(appDatabase: AppDatabase) : CharacterDao = appDatabase.characterDao()
}