package com.mobileexample.canonigo.data

import androidx.room.TypeConverter
import com.mobileexample.canonigo.network.ApiService
import com.mobileexample.canonigo.model.Character
import com.mobileexample.canonigo.utils.toCharacter
import com.mobileexample.canonigo.utils.toRoomCharacter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface Repository {
    suspend fun getCharacters(): List<Character>
    suspend fun getLocalCharacters(): List<Character>
    suspend fun saveCharactersToDatabase(characters: List<Character>)
}

class NetworkRepository(val api: ApiService, private val dao: RoomRickDao) : Repository {


    override suspend fun getCharacters(): List<Character> {
        val response = api.getCharacters()
        return response.characters
    }


    override suspend fun getLocalCharacters(): List<Character> {
        return dao.getAllCharacters().map { it.toCharacter() }
    }


    override suspend fun saveCharactersToDatabase(characters: List<Character>) {
        dao.insertCharacters(characters.map { it.toRoomCharacter() })
    }
}





