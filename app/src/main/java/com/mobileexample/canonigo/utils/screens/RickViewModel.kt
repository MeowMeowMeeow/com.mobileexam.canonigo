package com.mobileexample.canonigo.utils.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mobileexample.canonigo.RickMortyApplication
import com.mobileexample.canonigo.data.NetworkRepository
import com.mobileexample.canonigo.data.Repository

import com.mobileexample.canonigo.data.RoomRickDataBase
import com.mobileexample.canonigo.model.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface RickMortyUiState {
    object Loading : RickMortyUiState
    data class Success(val characters: List<Character>) : RickMortyUiState
    object Error : RickMortyUiState
}

class RickViewModel(private val repository: Repository) : ViewModel() {

    private val _rickMortyUiState = MutableStateFlow<RickMortyUiState>(RickMortyUiState.Loading)
    val rickMortyUiState: StateFlow<RickMortyUiState> = _rickMortyUiState

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            _rickMortyUiState.value = RickMortyUiState.Loading
            try {
                Log.d("RickViewModel", "Fetching characters from Room")
                var localCharacters = repository.getLocalCharacters()

                if (localCharacters.isEmpty()) {
                    Log.d("RickViewModel", "No data in Room, fetching from API...")
                    val apiCharacters = repository.getCharacters()

                    if (apiCharacters.isNotEmpty()) {
                        Log.d("RickViewModel", "Saving ${apiCharacters.size} characters to Room")
                        repository.saveCharactersToDatabase(apiCharacters)
                    }

                    // Fetch again from Room after saving
                    localCharacters = repository.getLocalCharacters()
                }


                localCharacters.forEach { character ->
                    Log.d("RickViewModel", "Character: ${character.name}, Origin: ${character.origin?.url}, Location: ${character.location?.url}")
                }

                _rickMortyUiState.value = RickMortyUiState.Success(localCharacters)

            } catch (e: IOException) {
                Log.e("RickViewModel", "Network error: ${e.message}")
                _rickMortyUiState.value = RickMortyUiState.Error
            } catch (e: HttpException) {
                Log.e("RickViewModel", "HTTP error: ${e.message}")
                _rickMortyUiState.value = RickMortyUiState.Error
            }
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as RickMortyApplication
                val database = RoomRickDataBase.getDatabase(application)
               val repository = NetworkRepository(

                   api = (application.container.characterRepository as NetworkRepository).api,
                dao = database.roomRickDao()
            )

                RickViewModel(repository = repository)
            }
        }
    }
}
