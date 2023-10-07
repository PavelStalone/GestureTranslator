package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.repository.WordCompilerRepository

class WordCompileUseCase(private val wordCompilerRepository: WordCompilerRepository) {
    fun addLetter(letter: String) {
        wordCompilerRepository.addLetter(letter)
    }

    fun clearState() {
        wordCompilerRepository.clearState()
    }

    fun getWord(): String {
        return wordCompilerRepository.getWord()
    }
}
