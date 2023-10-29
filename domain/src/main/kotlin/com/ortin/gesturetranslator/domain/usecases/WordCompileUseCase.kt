package com.ortin.gesturetranslator.domain.usecases

import com.ortin.gesturetranslator.domain.repository.WordCompilerRepository
import javax.inject.Inject

class WordCompileUseCase @Inject constructor(private val wordCompilerRepository: WordCompilerRepository) {
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
