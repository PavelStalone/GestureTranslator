package com.ortin.gesturetranslator.domain.entities

data class UserEntityDomain(
    val id: Int = 0,
    val login: String,
    val password: String,
    val isLoggedIn: Boolean = false
)
