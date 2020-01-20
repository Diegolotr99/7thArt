package com.diegogutierrez.seventhart.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class SeventhArtDatabase : RoomDatabase() {
    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            SeventhArtDatabase::class.java,
            "seventh-art-db"
        ).build()
    }

    abstract fun movieDao(): MovieDao
}