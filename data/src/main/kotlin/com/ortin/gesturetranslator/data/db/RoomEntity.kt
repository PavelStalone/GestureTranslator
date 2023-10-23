package com.ortin.gesturetranslator.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roomDB")
data class RoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val password: String,
    val isSigned: Boolean
)
