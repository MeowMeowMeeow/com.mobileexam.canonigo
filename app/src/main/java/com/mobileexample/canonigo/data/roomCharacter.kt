package com.mobileexample.canonigo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  "characters")
data class RoomCharacter(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val image: String,
    val gender: String,
    val originName: String,
    val originUrl: String,
    val locationName: String,
    val locationUrl: String,
    val episodeList: String
)
