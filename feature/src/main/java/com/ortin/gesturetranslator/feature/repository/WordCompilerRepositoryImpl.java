package com.ortin.gesturetranslator.feature.repository;

import com.ortin.gesturetranslator.domain.repository.WordCompilerRepository;
import com.ortin.gesturetranslator.feature.managers.word_compiler.WordCompilerManager;

public class WordCompilerRepositoryImpl implements WordCompilerRepository {
    private final WordCompilerManager wordCompilerManager;

    public WordCompilerRepositoryImpl(WordCompilerManager wordCompilerManager) {
        this.wordCompilerManager = wordCompilerManager;
    }

    @Override
    public void addLetter(String letter) {
        wordCompilerManager.addLetter(letter);
    }

    @Override
    public void clearState() {
        wordCompilerManager.clearState();
    }

    @Override
    public String getWord() {
        return wordCompilerManager.getWord();
    }
}
