package com.ortin.gesturetranslator.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        RoomEntity::class
    ],
    version = 1
)
abstract class RoomDB: RoomDatabase() {
    abstract val dao: Dao

    companion object {
        fun createDataBase(context: Context): RoomDB {
            return Room.databaseBuilder(
                context,
                RoomDB::class.java,
                "roomDB.db"
            ).build()
        }
    }
}
