package com.ortin.gesturetranslator.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    suspend fun insertItem(roomEntity: RoomEntity)
    @Update
    suspend fun updateItem(roomEntity: RoomEntity)
    @Delete
    suspend fun deleteItem(roomEntity: RoomEntity)
    @Query("SELECT * FROM roomDB")
    fun getAllItems(): Flow<List<RoomEntity>>
}
