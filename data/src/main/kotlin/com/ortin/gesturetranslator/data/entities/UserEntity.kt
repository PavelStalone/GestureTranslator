package com.ortin.gesturetranslator.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roomDB")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val login: String,
    val password: String,
    val isSigned: Boolean
)
