package com.mobileexample.canonigo

import android.app.Application
import com.mobileexample.canonigo.data.DefaultWubalubadubdub
import com.mobileexample.canonigo.data.RoomRickDataBase
import com.mobileexample.canonigo.data.Wubalubadubdub

class RickMortyApplication : Application() {
    lateinit var container: Wubalubadubdub

    override fun onCreate() {
        super.onCreate()

        val database = RoomRickDataBase.getDatabase(this)
        val dao = database.roomRickDao()

        container = DefaultWubalubadubdub(dao)
    }
}
