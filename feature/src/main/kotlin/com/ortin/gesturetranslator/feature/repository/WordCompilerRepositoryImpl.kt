package com.ortin.gesturetranslator.feature.repository

import com.ortin.gesturetranslator.domain.repository.WordCompilerRepository
import com.ortin.gesturetranslator.feature.managers.word_compiler.WordCompilerManager

class WordCompilerRepositoryImpl(private val wordCompilerManager: WordCompilerManager) :
    WordCompilerRepository {
    override fun addLetter(letter: String) {
        wordCompilerManager.addLetter(letter)
    }

    override fun clearState() {
        wordCompilerManager.clearState()
    }

    override fun getWord(): String {
        return wordCompilerManager.getWord()
    }
}
