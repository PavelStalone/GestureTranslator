package com.ortin.gesturetranslator.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ortin.gesturetranslator.data.entities.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1
)
abstract class UserRoomDB: RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        fun createDataBase(context: Context): UserRoomDB {
            return Room.databaseBuilder(
                context,
                UserRoomDB::class.java,
                "UserInfo.db"
            ).build()
        }
    }
}
