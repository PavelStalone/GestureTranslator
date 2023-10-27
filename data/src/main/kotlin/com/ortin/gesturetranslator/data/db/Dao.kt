package com.ortin.gesturetranslator.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ortin.gesturetranslator.data.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    suspend fun insertUser(userEntity: UserEntity)
    @Update
    suspend fun updateUser(userEntity: UserEntity)
    @Delete
    suspend fun deleteUser(userEntity: UserEntity)
    @Query("SELECT * FROM roomDB")
    fun getAllUsers(): Flow<List<UserEntity>>
    @Query("SELECT * FROM roomDB WHERE login == :login")
    fun getUser(login: String): UserEntity
}
