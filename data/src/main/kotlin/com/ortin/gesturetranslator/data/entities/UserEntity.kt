package com.ortin.gesturetranslator.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userRoomDB")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val login: String,
    val password: String,
    val isSigned: Boolean
)
