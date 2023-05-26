package com.ortin.gesturetranslator.feature.managers.word_compiler;

public interface WordCompilerManager {
    void addLetter(String letter);
    void clearState();
    String getWord();
}
