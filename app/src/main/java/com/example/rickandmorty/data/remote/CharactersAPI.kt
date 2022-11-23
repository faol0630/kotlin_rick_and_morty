package com.example.rickandmorty.data.remote

import com.example.rickandmorty.application.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersAPI {

    fun getCharacters(): CharactersService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CharactersService::class.java)
    }
}