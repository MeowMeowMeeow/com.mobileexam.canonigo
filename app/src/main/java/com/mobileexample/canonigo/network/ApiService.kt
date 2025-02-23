package com.mobileexample.canonigo.network

import com.mobileexample.canonigo.model.CharacterResponse
import retrofit2.http.GET

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): CharacterResponse
}

