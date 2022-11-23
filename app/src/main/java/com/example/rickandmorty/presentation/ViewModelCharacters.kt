package com.example.rickandmorty.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.core.SealedClass1
import com.example.rickandmorty.data.model.Character1
import com.example.rickandmorty.data.model.CharacterEntity
import com.example.rickandmorty.repository.RepositoryInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelCharacters @Inject constructor(
    private val repository: RepositoryInt
) : ViewModel(){

    private var _allCharactersVM = MutableLiveData<SealedClass1>()
    val allCharactersVM :LiveData<SealedClass1>
        get() = _allCharactersVM

    fun getAllCharactersVM(page: Int){
        viewModelScope.launch {
            _allCharactersVM.postValue(SealedClass1.Loading)

            try {
                val repo = repository.getAllCharacters(page)
                _allCharactersVM.postValue(SealedClass1.Content(repo))
            }catch (e:Exception){
                _allCharactersVM.postValue(SealedClass1.Error)
            }
        }
    }

    //------------

    private val _itemDetails = MutableLiveData<Character1>()
    val itemDetails: LiveData<Character1>
        get() = _itemDetails

    //send character to FragmentDetails :
    fun sendDetails(character: Character1) {
        _itemDetails.value = character
    }

    //Room---------

    //from Fragment Details to Fragments Favorites:

    fun insertCharacterToFavorites(characterEntity: CharacterEntity) {
        viewModelScope.launch {
            repository.insertCharacterToFavorites(characterEntity)
        }
    }

    //used in Fragment Details Favorites to delete character in Fragment Favorites:

    fun deleteCharacterFromFavorites(characterEntity: CharacterEntity){
        viewModelScope.launch {
            repository.deleteCharacterFromFavorites(characterEntity)
        }
    }

    //see all favorite phrases in FragmentFavorites:

    private var _favList = MutableLiveData<List<CharacterEntity>>()
    val favList: LiveData<List<CharacterEntity>>
        get() = _favList


    fun getFavoritesCharactersVM(): LiveData<List<CharacterEntity>> {

        viewModelScope.launch {
            _favList.value = repository.getFavoritesCharacters()
        }
        return _favList

    }

}