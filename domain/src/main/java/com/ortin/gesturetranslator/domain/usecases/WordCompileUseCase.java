package com.ortin.gesturetranslator.domain.usecases;

import com.ortin.gesturetranslator.domain.repository.WordCompilerRepository;

public class WordCompileUseCase {

    private final WordCompilerRepository wordCompilerRepository;

    public WordCompileUseCase(WordCompilerRepository wordCompilerRepository) {
        this.wordCompilerRepository = wordCompilerRepository;
    }

    public void addLetter(String letter) {
        wordCompilerRepository.addLetter(letter);
    }

    public void clearState() {
        wordCompilerRepository.clearState();
    }

    public String getWord() {
        return wordCompilerRepository.getWord();
    }
}
