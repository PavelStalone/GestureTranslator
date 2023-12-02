package com.ortin.gesturetranslator.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ortin.gesturetranslator.data.entities.UserEntity

private const val DATABASE_NAME = "user_info"

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        fun buildDataBase(context: Context): UserDatabase {
            return Room.databaseBuilder(
                context,
                UserDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}
