package com.mobileexample.canonigo.utils.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mobileexample.canonigo.RickMortyApplication
import com.mobileexample.canonigo.data.Repository
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
                Log.d("RickViewModel", "Fetching characters from repository")
                val characters = repository.getCharacters()
                Log.d("RickViewModel", "Fetched ${characters.size} characters")
                _rickMortyUiState.value = RickMortyUiState.Success(characters)
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
                val repository = application.container.characterRepository
                RickViewModel(repository = repository)
            }
        }
    }
}