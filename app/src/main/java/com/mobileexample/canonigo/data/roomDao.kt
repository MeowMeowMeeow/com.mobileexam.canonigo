package com.mobileexample.canonigo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomRickDao {
    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<RoomCharacter>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: List<RoomCharacter>)
}
