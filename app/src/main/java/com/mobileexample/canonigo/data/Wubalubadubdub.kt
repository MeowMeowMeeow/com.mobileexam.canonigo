package com.mobileexample.canonigo.data



import androidx.room.Database
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mobileexample.canonigo.network.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface Wubalubadubdub{
val characterRepository: Repository
}

class DefaultWubalubadubdub(private val dao: RoomRickDao) : Wubalubadubdub {
    private val BASE_URL = "https://rickandmortyapi.com/api/"

    private val json = Json {
        ignoreUnknownKeys = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val characterRepository: Repository by lazy {
        NetworkRepository(
            api = retrofitService,
            dao = dao
        )
    }
}
