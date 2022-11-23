package com.example.rickandmorty.core

import com.example.rickandmorty.data.model.AllCharacters

sealed class SealedClass1{
    object Loading: SealedClass1()
    data class Content(val sealedList: AllCharacters): SealedClass1()
    object Error: SealedClass1()
}

