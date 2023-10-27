package com.ortin.gesturetranslator.domain.entities

data class UserEntityDomain(
    val id: Int?,
    val login: String,
    val password: String,
    val isSigned: Boolean = false
)
