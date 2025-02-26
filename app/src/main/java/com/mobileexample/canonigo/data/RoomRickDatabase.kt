package com.mobileexample.canonigo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room


@Database(entities = [RoomCharacter::class], version = 2, exportSchema = false)
abstract class RoomRickDataBase : RoomDatabase() {
    abstract fun roomRickDao(): RoomRickDao

    companion object {
        @Volatile
        private var INSTANCE: RoomRickDataBase? = null

        fun getDatabase(context: Context): RoomRickDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomRickDataBase::class.java,
                    "rick_and_morty_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}
