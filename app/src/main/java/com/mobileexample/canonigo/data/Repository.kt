package com.mobileexample.canonigo.data

import com.mobileexample.canonigo.network.ApiService
import com.mobileexample.canonigo.model.Character

interface Repository {
    suspend fun getCharacters(): List<Character>
}

class NetworkRepository(private val api: ApiService) : Repository {
    override suspend fun getCharacters(): List<Character> {
        val response = api.getCharacters()
        return response.characters
    }
}