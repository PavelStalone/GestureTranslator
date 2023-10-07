package com.ortin.gesturetranslator.domain.repository

interface WordCompilerRepository {
    fun addLetter(letter: String)

    fun clearState()

    fun getWord(): String
}
