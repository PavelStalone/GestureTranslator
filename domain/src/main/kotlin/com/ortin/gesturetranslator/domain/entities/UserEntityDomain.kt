package com.ortin.gesturetranslator.domain.entities

data class UserEntityDomain(
    val login: String,
    val password: String,
    val isSigned: Boolean = false
)
