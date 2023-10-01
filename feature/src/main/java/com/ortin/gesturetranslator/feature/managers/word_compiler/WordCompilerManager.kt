package com.ortin.gesturetranslator.feature.managers.word_compiler

interface WordCompilerManager {
    fun addLetter(letter: String?)
    fun clearState()
    fun getWord(): String
}