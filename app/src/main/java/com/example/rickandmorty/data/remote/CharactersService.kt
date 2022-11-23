package com.example.rickandmorty.data.remote

import com.example.rickandmorty.application.RESULTS
import com.example.rickandmorty.data.model.AllCharacters
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {

    @GET(RESULTS)
    suspend fun getAllCharacters(@Query("page") page: Int): AllCharacters

}