package com.ortin.gesturetranslator.domain.repository;

public interface WordCompilerRepository {
    void addLetter(String letter);
    void clearState();
    String getWord();
}
